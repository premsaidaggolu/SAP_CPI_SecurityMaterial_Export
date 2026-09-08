import com.sap.gateway.ip.core.customdev.util.Message
import groovy.xml.MarkupBuilder
import com.sap.it.api.ITApiFactory
import com.sap.it.api.securestore.SecureStoreService

def Message processData(Message message) {
 def byteArray = message.getBody(byte[])
 def xmlResponse = new String(byteArray, "UTF-8").replaceAll("[^\\x00-\\x7F]", "") // Remove non-ASCII chars

 def parsedXml = new XmlSlurper().parseText(xmlResponse)
 def writer = new StringWriter()
 def xmlBuilder = new MarkupBuilder(writer)

 def service = ITApiFactory.getApi(SecureStoreService.class, null)

    xmlBuilder.SecurityArtifacts {
        parsedXml.entry.each {
            entryNode ->
            def credType = entryNode.title.text().trim() \

 // If credType is "UserCredentials", filter records where Kind == "default" - using this condition to exclude other Credential Types.
 def filteredCredentials = entryNode.content.properties.findAll {
                credType == "UserCredentials" ? it.Kind?.text()?.trim() == "default" : true
            }

            filteredCredentials.each {
                Credentials ->
                def credName = Credentials.Name?.text() ?: "" 
                def description = Credentials.Description?.text() ?: ""
                def userName = Credentials.User?.text() ?: "" 
                def clientId = Credentials.ClientId?.text() ?: "" 
                def tokenUrl = Credentials.TokenServiceUrl?.text() ?: "" 
                def secureParam = ""
                def password = ""
                def remark = ""

                if (!credName) {
                    remark = "Missing Credential Name"
                } else if (service == null) {
                    password = ""
                    remark = "Secure Store Service Unavailable"
                } else {
                    def PasswordObj = service.getUserCredential(credName)
                    if (PasswordObj) {
                        password = new String(PasswordObj.getPassword())
                        secureParam = password
                    } else {
                        password = ""
                        secureParam = ""
                        remark = "No Credential Found"
                    }
                }

 // Creating XML for all credential types
 CredentialsDetails {
                    Name(credName ?: "N/A")
                    Type(credType)
                    Description(description)
                    
                    // username and password for user credentials
                    if (credType == "UserCredentials") {
                        UserName(userName)
                        Password(password)
                    } else {
                        UserName("")
                        Password("")
                    }

                    // client id, secret and token url for OAuth 2.0
                    if (credType == "OAuth2ClientCredentials") {
                        ClientId(clientId)
                        ClientSecret(password)
                        TokenUrl(tokenUrl)
                    } else {
                        ClientId("")
                        ClientSecret("")
                        TokenUrl("")
                    }
                    
                    // secure parameter value
                    if (credType == "SecureParameters") {
                        SecureParameters(secureParam)
                    } else {
                        SecureParameters("")
                    }

                    Comments(remark)
                }
            }
        }
    }
    // return created XML payload
    message.setBody(writer.toString())
    return message
}

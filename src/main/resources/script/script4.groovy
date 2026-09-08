import com.sap.gateway.ip.core.customdev.util.Message;

def Message processData(Message message) 
{
    def logFileProp = message.getProperty("logFile");
    //log as attachment only if logFile property value is true
    if(logFileProp == "true"){
    
	def body = message.getBody(java.lang.String) as String;
	def messageLog = messageLogFactory.getMessageLog(message);
	if(messageLog != null)
	{
	messageLog.addAttachmentAsString("CredentialDetails", body, "text/csv");
     }
     
    }
	return message;
}

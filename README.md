# SAP_CPI_SecurityMaterial_Export

This SAP Cloud Integration (CPI) artifact automates the retrieval of **User Credentials, OAuth2 Client Credentials, and Secure Parameters** from CPI’s **Security Material**. The extracted data is formatted as a **CSV file**, **encrypted using PGP**, and sent as an **email attachment** to ensure secure credential handling.  
**Features**  
✅ Fetch User Credentials, OAuth2 Client Credentials, and Secure Parameters from Security Material    
✅ Format the credentials into a structured CSV file  
✅ Apply PGP encryption to protect sensitive information  
✅ Send the encrypted file via email as an attachment  
✅ Ensure compliance by preventing local storage risks  
✅ Decrypt the encrypted file securely when needed  

Installation
Import the Artifact into SAP CPI
1. Download the **Security Material Export with PGP Encryption.zip** from this repository.
2. Log in to **SAP CPI**.
3. Navigate to **Design > Your Package > Artifacts > Add > Integration Flow**.
4. Click **Import** and upload the `Security Material Export with PGP Encryption.zip` file.
5. Configure Required Parameters  
**Click on Configuration**, update the following properties:
**CPI Receiver** --> Authentication, Credential Name  
**Mail Receiver**	--> From, To, CC (optional), Credential Name  
**More** --> Log File as Attachment, Tenant URL, PGP Public Key, Security Key Length.
**Note:** Ensure that the **PGP public key** is uploaded in CPI’s **Security Material** section.
Usage
1. **Trigger the iFlow** via a manual test or scheduled execution.
2. **Monitor the execution** in CPI’s **Monitoring Dashboard**.
3. **Check the email inbox** of the recipient to find the encrypted file.

To view the original CSV data, follow these steps:
1. Use a **PGP decryption tool** such as **GnuPG, Kleopatra, or OpenPGP Studio**.
2. Import your **PGP private key** into the tool.
3. Decrypt the email attachment to retrieve the original **CSV file**.

**Blog & Additional Resources**
For a detailed explanation, check out the **SAP Blog**:  
📌 https://community.sap.com/t5/technology-blogs-by-members/fetching-security-materials-in-sap-cpi-and-sending-it-as-a-pgp-encrypted/ba-p/14011652

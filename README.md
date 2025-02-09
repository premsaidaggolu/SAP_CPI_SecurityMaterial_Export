# SAP_CPI_SecurityMaterial_Export

SAP CPI – Fetch & Encrypt Security Material
Overview
This SAP Cloud Integration (CPI) artifact automates the retrieval of **User Credentials, OAuth2 Client Credentials, and Secure Parameters** from CPI’s **Security Material**. The extracted data is formatted as a **CSV file**, **encrypted using PGP**, and sent as an **email attachment** to ensure secure credential handling.
Features
✅ Fetch User Credentials, OAuth2 Client Credentials, and Secure Parameters from Security Material
✅ Format the credentials into a structured CSV file
✅ Apply PGP encryption to protect sensitive information
✅ Send the encrypted file via email as an attachment
✅ Ensure compliance by preventing local storage risks
✅ Decrypt the encrypted file securely when needed
Installation
1. Import the Artifact into SAP CPI
1. Download the **iFlow artifact (.zip)** from this repository (`iFlow/` folder).
2. Log in to **SAP CPI Web UI**.
3. Navigate to **Design > Your Package > Artifacts > Add > Integration Flow**.
4. Click **Import** and upload the `.zip` file.
5. Deploy the iFlow after successful import.
2. Configure Required Parameters
In the **iFlow’s Integration Process**, update the following properties:
Property Name	Description
MailReceiver	Email ID of the recipient
PGP_PublicKey	The public key used for encryption
CredentialName	Name of the Security Material credential to fetch
SecureParamName	Secure Parameter to retrieve
**Note:** Ensure that the **PGP public key** is uploaded in CPI’s **Security Material** section.
Usage
1. **Trigger the iFlow** via a manual test or scheduled execution.
2. **Monitor the execution** in CPI’s **Monitoring Dashboard**.
3. **Check the email inbox** of the recipient to find the encrypted CSV file.
Decrypting the Encrypted File
To view the original CSV data, follow these steps:
1. Use a **PGP decryption tool** such as **GnuPG, Kleopatra, or OpenPGP Studio**.
2. Import your **PGP private key** into the tool.
3. Decrypt the email attachment to retrieve the original **CSV file**.
Sample Files
You can find example output files in the `Samples/` folder:
- `Encrypted_Credentials.pgp` – Sample encrypted file
- `Decrypted_Credentials.csv` – Sample decrypted CSV file
License
This project is licensed under the **MIT License** – feel free to modify and use it as needed.
Blog & Additional Resources
For a detailed explanation, check out the **SAP Blog**:
📌 **[Link to Blog Post]** (Add your blog link here)

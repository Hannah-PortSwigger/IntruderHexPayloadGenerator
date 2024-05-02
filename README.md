# Intruder Hex Payload Generator

This extension inserts hex bytes as a payload in Intruder.

## Usage
1. In `Extension.java`, modify the `payloads` list to contain your hex characters. You can provide individual character pairs, or multiple, as per the examples already present.
2. Build the extension using `./gradlew jar`
3. Load the extension JAR located in `build/libs` into Burp (Extensions > Installed > Add).
4. Configure your Intruder attack. Change "Payload type" to "Extension-generated" and select the appropriate payload generator.
5. Start your attack.
6. Verify that your payloads have been inserted by check the "Hex" tab of your outgoing Intruder request and checking your payload position.
import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import burp.api.montoya.core.ByteArray;
import burp.api.montoya.intruder.*;

import java.util.HexFormat;
import java.util.List;

import static burp.api.montoya.core.ByteArray.byteArray;
import static burp.api.montoya.intruder.GeneratedPayload.end;
import static burp.api.montoya.intruder.GeneratedPayload.payload;

public class Extension implements BurpExtension
{
    @Override
    public void initialize(MontoyaApi montoyaApi)
    {
        String extensionName = "Intruder hex payload generator";
        montoyaApi.extension().setName(extensionName);

        List<ByteArray> payloads = List.of(
                byteArray(HexFormat.of().parseHex("1f")),
                byteArray(HexFormat.of().parseHex("a0")),
                byteArray(HexFormat.of().parseHex("0d0a")),
                byteArray(HexFormat.of().parseHex("0d")),
                byteArray(HexFormat.of().parseHex("0c")),
                byteArray(HexFormat.of().parseHex("1f2c"))
        );

        montoyaApi.intruder().registerPayloadGeneratorProvider(new PayloadGeneratorProvider()
        {
            @Override
            public String displayName()
            {
                return extensionName;
            }

            @Override
            public PayloadGenerator providePayloadGenerator(AttackConfiguration attackConfiguration)
            {
                return new PayloadGenerator()
                {
                    private int payloadIndex;

                    @Override
                    public GeneratedPayload generatePayloadFor(IntruderInsertionPoint intruderInsertionPoint)
                    {
                        if (payloadIndex > payloads.size())
                        {
                            return end();
                        }

                        ByteArray payload = payloads.get(payloadIndex);
                        montoyaApi.logging().logToOutput("payload #" + payloadIndex + " : " + payload.toString());

                        payloadIndex++;

                        return payload(payload);
                    }
                };
            }
        });
    }
}

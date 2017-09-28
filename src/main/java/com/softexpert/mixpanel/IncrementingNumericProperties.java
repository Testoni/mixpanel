/**
 * 
 */
package com.softexpert.mixpanel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.mixpanel.mixpanelapi.MessageBuilder;
import com.mixpanel.mixpanelapi.MixpanelAPI;
import com.mixpanel.mixpanelapi.MixpanelMessageException;
import com.softexpert.utils.Config;

/**
 * @author gabriel.testoni
 *
 */
public class IncrementingNumericProperties {

    public static void main(String[] args) throws MixpanelMessageException, IOException {

        MessageBuilder messageBuilder = new MessageBuilder(Config.PROJECT_TOKEN);

        // Pass a Map to increment multiple properties
        Map<String, Long> properties = new HashMap<String, Long>();
        properties.put("dollars spent", (long) 17);

        // Subtract by passing a negative value
        properties.put("credits remaining", (long) -34);
        JSONObject update = messageBuilder.increment("13793", properties);

        // Send the update to mixpanel
        MixpanelAPI mixpanel = new MixpanelAPI();
        mixpanel.sendMessage(update);

        System.out.println("Success!");

    }

}

/**
 * 
 */
package com.softexpert.mixpanel;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.mixpanel.mixpanelapi.MessageBuilder;
import com.mixpanel.mixpanelapi.MixpanelAPI;
import com.mixpanel.mixpanelapi.MixpanelMessageException;
import com.softexpert.utils.Config;

/**
 * @author gabriel.testoni
 *
 */
public class StoringUserProfiles {

    public static void main(String[] args) throws JSONException, MixpanelMessageException, IOException {

        MessageBuilder messageBuilder = new MessageBuilder(Config.PROJECT_TOKEN);

        // Sets user 13793's "Plan" attribute to "Premium"
        // This creates a profile for 13793 if one does not
        // already exist.
        JSONObject props = new JSONObject();
        props.put("Plan", "Premium");

        JSONObject update = messageBuilder.set("13793", props);

        // Send the update to mixpanel
        MixpanelAPI mixpanel = new MixpanelAPI();
        mixpanel.sendMessage(update);

        System.out.println("Success!");

    }

}

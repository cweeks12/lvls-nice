package com.nice;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("example")
public interface NiceConfig extends Config
{
	@ConfigItem(
			position = 1,
		keyName = "responses",
		name = "Responses",
		description = "The comma-separated messages to choose from."
	)
	default String responses()
	{
		return "Nice., Nice!, Nice";
	}

	@ConfigItem(
			position = 2,
			keyName = "partyLength",
			name = "Party Length (seconds)",
			description = "Number of seconds to spread the messages over"
	)
	default int partyLength(){
		return 5;
	}
}


package com.nice;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("example")
public interface NiceConfig extends Config
{
	@ConfigItem(
		keyName = "responses",
		name = "Responses",
		description = "The comma-separated messages to choose from."
	)
	default String responses()
	{
		return "Nice., Nice!, Nice";
	}


}

package com.nice;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.*;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import java.util.*;

@Slf4j
@PluginDescriptor(
	name = "Lvls? Nice"
)
public class NicePlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private NiceConfig config;

	private Set<Player> playerSet = new HashSet<>();
	private List<String> wordList = new ArrayList<>();
	private Random random = new Random();

	@Override
	protected void startUp() throws Exception
	{
		log.info("Lvls? Nice started!");
		recreateWordList();
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Lvls? Nice stopped!");
		playerSet.clear();
	}

	@Subscribe
	public void onPlayerSpawned(PlayerSpawned event)
	{
		if (event.getPlayer() != client.getLocalPlayer())
		{
			playerSet.add(event.getPlayer());
		}
	}

	@Subscribe
	public void onPlayerDespawned(PlayerDespawned event)
	{
		playerSet.remove(event.getPlayer());
	}

	@Subscribe
	public void onChatMessage(ChatMessage event){

		// If it's not a public message, we don't care
		if (event.getType() != ChatMessageType.PUBLICCHAT){
			return;
		}

		String message = event.getMessage().trim();

		if (message.contains("!Lvl")){

			recreateWordList();
			showNice();
		}

	}

	private void recreateWordList(){
		wordList = Arrays.asList(config.responses().split("\\s*,\\s*"));
	}

	private void showNice(){

		List<PlayerMessageThread> threads = new ArrayList<>();

		for (Player player: playerSet){
			threads.add(new PlayerMessageThread(player, wordList.get(random.nextInt(wordList.size()))));
		}

		BigChatThread chatThread = new BigChatThread(threads, config.partyLength() * 1000f);

		chatThread.start();

	}

	@Provides
	NiceConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(NiceConfig.class);
	}
}

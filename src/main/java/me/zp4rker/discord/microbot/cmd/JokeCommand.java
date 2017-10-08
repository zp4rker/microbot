package me.zp4rker.discord.microbot.cmd;

import me.zp4rker.discord.core.command.ICommand;
import me.zp4rker.discord.core.command.RegisterCommand;
import me.zp4rker.discord.core.logger.ZLogger;
import me.zp4rker.discord.microbot.util.MessageUtil;
import me.zp4rker.discord.microbot.util.ColorUtil;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * @author ZP4RKER
 */
public class JokeCommand implements ICommand {

    @RegisterCommand(aliases = "joke",
            description = "Tells a joke.",
            usage = "{prefix}joke")
    public void onCommand(Message message) {
        MessageUtil.bypassDeleteLogs(message);
        try {
            /*Yaml yaml = new Yaml();
            yaml.loadFromString(YamlUtil.fromUrl("http://tambal.azurewebsites.net/joke/random"));

            message.getChannel().sendMessage(new EmbedBuilder()
                    .setTitle(yaml.getString("joke"))
                    .setColor(ColorUtil.randomColour()).build()).complete();*/
            URL url = new URL("https://icanhazdadjoke.com/");
            URLConnection uc = url.openConnection();
            uc.addRequestProperty("Accept", "text/plain");
            uc.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
            InputStream is = uc.getInputStream();
            Scanner scanner = new Scanner(is);
            scanner.useDelimiter("\\A");
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNext()) {
                sb.append(scanner.next());
            }
            is.close();

            message.getChannel().sendMessage(new EmbedBuilder()
                    .setTitle(sb.toString())
                    .setColor(ColorUtil.randomColour()).build()).complete();
        } catch (Exception e) {
            e.printStackTrace();
            ZLogger.warn("Could not send joke!");
        }
    }

}
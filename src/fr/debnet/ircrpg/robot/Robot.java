package fr.debnet.ircrpg.robot;

import fr.debnet.ircbot.DccChat;
import fr.debnet.ircbot.DccFileTransfer;
import fr.debnet.ircbot.IRCBot;
import fr.debnet.ircbot.User;
import fr.debnet.ircrpg.Config;
import fr.debnet.ircrpg.Strings;
import fr.debnet.ircrpg.enums.Activity;
import fr.debnet.ircrpg.game.Game;
import fr.debnet.ircrpg.helpers.Helpers;
import fr.debnet.ircrpg.interfaces.INotifiable;
import fr.debnet.ircrpg.models.Result;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * IRC robot implementation
 * @author Marc
 */
public class Robot extends IRCBot implements INotifiable {

    private Game game;
    
    public Robot(Game game) {
        this.game = game;
        this.game.registerNotifiable(this);
        this.setVerbose(Config.DEBUG);
    }
    
    public void connect() {
        try {
            this.startIdentServer();
            this.setName(Config.IRC_NICKNAME);
            this.setEncoding(Config.IRC_CHARSET);
            super.connect(Config.IRC_SERVER, Config.IRC_PORT, Config.IRC_PASSWORD);
            this.joinChannel(Config.IRC_CHANNEL);
        } catch (Exception ex) {
            Logger.getLogger(Robot.class.getName()).severe(ex.getLocalizedMessage());
            System.exit(-1);
        }
    }
    
    private void sendFormattedMessage(String message, Object... args) {
        if (this.isConnected()) {
            for (String channel : this.getChannels()) {
                message = String.format(message, args);
                message = Strings.formatMessage(message);
                super.sendMessage(channel, message);
            }
        }
    }
    
    private void sendFormattedMessage(String target, String message, Object... args) {
        if (this.isConnected()) {
            message = String.format(message, args);
            message = Strings.formatMessage(message);
            super.sendMessage(target, message);
        }
    }

    private void processMessage(String sender, String hostname, String message) {
        String[] words = message.split(" ");
        if (words.length > 0) {
            String command = words[0].toLowerCase();
            if (!command.startsWith("!")) {
                this.sendFormattedMessage(sender, Strings.RETURN_UNKNOWN_COMMAND);
                return;
            }
            // Register
            if (Strings.COMMAND_REGISTER.equals(command)) {
                if (words.length == 3) {
                    String username = words[1];
                    String password = words[2];
                    Result result = this.game.register(username, password, sender, hostname);
                    this.displayResult(result, sender);
                } else this.sendFormattedMessage(sender, help.get(command));
            }
            // Login
            else if (Strings.COMMAND_LOGIN.equals(command)) {
                if (words.length == 3) {
                    String username = words[1];
                    String password = words[2];
                    Result result = this.game.login(username, password, sender, hostname);
                    this.displayResult(result, sender);
                } else this.sendFormattedMessage(sender, help.get(command));
            }
            // Attack
            else if (Strings.COMMAND_ATTACK.equals(command)) {
                if (words.length == 2) {
                    String target = words[1];
                    Result result = this.game.fight(sender, target, null);
                    this.displayResult(result, sender);
                } else this.sendFormattedMessage(sender, help.get(command));
            }
            // Magic
            else if (Strings.COMMAND_MAGIC.equals(command)) {
                if (words.length > 2) {
                    String target = words[1];
                    String magic = "";
                    for (int i = 1; i < words.length - 2; i++) {
                        magic += " " + words[i];
                        magic = magic.trim();
                    }
                    Result result = this.game.fight(sender, target, magic);
                    this.displayResult(result, sender);
                } else this.sendFormattedMessage(sender, help.get(command));
            }
            // Steal
            else if (Strings.COMMAND_STEAL.equals(command)) {
                if (words.length == 2) {
                    String target = words[1];
                    Result result = this.game.steal(sender, target);
                    this.displayResult(result, sender);
                } else this.sendFormattedMessage(sender, help.get(command));
            }
            // Work
            else if (Strings.COMMAND_WORK.equals(command)) {
                if (words.length == 1) {
                    Result result = this.game.startActivity(sender, Activity.WORKING.toString());
                    this.displayResult(result, sender);
                } else this.sendFormattedMessage(sender, help.get(command));
            }
            // Rest
            else if (Strings.COMMAND_REST.equals(command)) {
                if (words.length == 1) {
                    Result result = this.game.startActivity(sender, Activity.RESTING.toString());
                    this.displayResult(result, sender);
                } else this.sendFormattedMessage(sender, help.get(command));
            }
            // Train
            else if (Strings.COMMAND_TRAIN.equals(command)) {
                if (words.length == 1) {
                    Result result = this.game.startActivity(sender, Activity.TRAINING.toString());
                    this.displayResult(result, sender);
                } else this.sendFormattedMessage(sender, help.get(command));
            }
            // Return
            else if (Strings.COMMAND_RETURN.equals(command)) {
                if (words.length == 1) {
                    Result result = this.game.endActivity(sender);
                    this.displayResult(result, sender);
                } else this.sendFormattedMessage(sender, help.get(command));
            }
            // Buy
            else if (Strings.COMMAND_BUY.equals(command)) {
                if (words.length > 1) {
                    String item = "";
                    for (int i = 1; i < words.length - 1; i++) {
                        item += " " + words[i];
                        item = item.trim();
                    }
                    Result result = this.game.buy(sender, item);
                    this.displayResult(result, sender);
                } else this.sendFormattedMessage(sender, help.get(command));
            }
            // Sell
            else if (Strings.COMMAND_SELL.equals(command)) {
                if (words.length > 1) {
                    String item = "";
                    for (int i = 1; i < words.length - 1; i++) {
                        item += " " + words[i];
                        item = item.trim();
                    }
                    Result result = this.game.buy(sender, item);
                    this.displayResult(result, sender);
                } else this.sendFormattedMessage(sender, help.get(command));
            }
            // Drink
            else if (Strings.COMMAND_DRINK.equals(command)) {
                if (words.length > 1) {
                    String item = "";
                    for (int i = 1; i < words.length - 1; i++) {
                        item += " " + words[i];
                        item = item.trim();
                    }
                    Result result = this.game.drink(sender, item);
                    this.displayResult(result, sender);
                } else this.sendFormattedMessage(sender, help.get(command));
            }
            // Learn
            else if (Strings.COMMAND_LEARN.equals(command)) {
                if (words.length > 1) {
                    String spell = "";
                    for (int i = 1; i < words.length - 1; i++) {
                        spell += " " + words[i];
                        spell = spell.trim();
                    }
                    Result result = this.game.learn(sender, spell);
                    this.displayResult(result, sender);
                } else this.sendFormattedMessage(sender, help.get(command));
            }
            // Level up
            else if (Strings.COMMAND_LEVELUP.equals(command)) {
                if (words.length == 2) {
                    Result result = this.game.upgrade(sender, words[1]);
                    this.displayResult(result, sender);
                } else this.sendFormattedMessage(sender, help.get(command));
            }
            // Infos
            else if (Strings.COMMAND_INFOS.equals(command)) {
                String nickname = words.length == 2 ? words[2] : sender;
                String string = this.game.showInfos(nickname);
                if (string != null) this.sendFormattedMessage(sender, string);
            }
            // Stats
            else if (Strings.COMMAND_STATS.equals(command)) {
                String nickname = words.length == 2 ? words[2] : sender;
                String string = this.game.showStats(nickname);
                if (string != null) this.sendFormattedMessage(sender, string);
            }
            // Items 
            else if (Strings.COMMAND_ITEMS.equals(command)) {
                String nickname = words.length == 2 ? words[2] : sender;
                String string = this.game.showItems(nickname);
                if (string != null) this.sendFormattedMessage(sender, string);
            }
            // Spells 
            else if (Strings.COMMAND_SPELLS.equals(command)) {
                String nickname = words.length == 2 ? words[2] : sender;
                String string = this.game.showSpells(nickname);
                if (string != null) this.sendFormattedMessage(sender, string);
            }
            // Look
            else if (Strings.COMMAND_LOOK.equals(command)) {
                if (words.length > 1) {
                    String code = words[2];
                    String string = this.game.look(code);
                    if (string != null) this.sendFormattedMessage(sender, string);
                } else this.sendFormattedMessage(sender, help.get(command));
            }
            // Help
            else if (Strings.COMMAND_HELP.equals(command)) {
                if (words.length > 1 && help.containsKey(words[1])) {
                    this.sendFormattedMessage(sender, help.get(words[1]));
                } else this.sendFormattedMessage(sender, help.get(command));
            }
        }
    }
    
    private void displayResult(Result result, String sender) {
        if (result.isSuccess()) {
            this.sendFormattedMessage(Helpers.getMessage(result));
        } else {
            this.sendFormattedMessage(sender, Helpers.getMessage(result));
        }
    }
    
    @Override
    public void notify(Result result) {
        for (String channel : this.getChannels()) {
            this.sendFormattedMessage(channel, Helpers.getMessage(result));
        }
    }
    
    @Override
    protected void onConnect() {
        
    }

    @Override
    protected void onDisconnect() {
        this.game.disconnect();
    }

    @Override
    protected void onServerResponse(int code, String response) {
        
    }

    @Override
    protected void onUserList(String channel, User[] users) {
        
    }

    @Override
    protected void onMessage(String channel, String sender, String login, String hostname, String message) {
        this.processMessage(sender, hostname, message);
    }

    @Override
    protected void onPrivateMessage(String sender, String login, String hostname, String message) {
        this.processMessage(sender, hostname, message);
    }

    @Override
    protected void onAction(String sender, String login, String hostname, String target, String action) {
        
    }

    @Override
    protected void onNotice(String sourceNick, String sourceLogin, String sourceHostname, String target, String notice) {
        
    }

    @Override
    protected void onJoin(String channel, String sender, String login, String hostname) {
        Result result = this.game.tryRelogin(sender, hostname);
        if (result.isSuccess()) this.displayResult(result, sender);
    }

    @Override
    protected void onPart(String channel, String sender, String login, String hostname) {
        Result result = this.game.logout(sender);
        this.displayResult(result, sender);
    }

    @Override
    protected void onNickChange(String oldNick, String login, String hostname, String newNick) {
        
    }

    @Override
    protected void onKick(String channel, String kickerNick, String kickerLogin, String kickerHostname, String recipientNick, String reason) {
        
    }

    @Override
    protected void onQuit(String sourceNick, String sourceLogin, String sourceHostname, String reason) {
        Result result = this.game.logout(sourceNick);
        this.displayResult(result, sourceNick);
    }

    @Override
    @SuppressWarnings("deprecation")
    protected void onTopic(String channel, String topic) {
        
    }

    @Override
    protected void onTopic(String channel, String topic, String setBy, long date, boolean changed) {
        
    }

    @Override
    protected void onChannelInfo(String channel, int userCount, String topic) {
        
    }

    @Override
    protected void onMode(String channel, String sourceNick, String sourceLogin, String sourceHostname, String mode) {
        
    }

    @Override
    protected void onUserMode(String targetNick, String sourceNick, String sourceLogin, String sourceHostname, String mode) {
        
    }

    @Override
    protected void onOp(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {
        
    }

    @Override
    protected void onDeop(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {
        
    }

    @Override
    protected void onVoice(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {
        
    }

    @Override
    protected void onDeVoice(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {
        
    }

    @Override
    protected void onSetChannelKey(String channel, String sourceNick, String sourceLogin, String sourceHostname, String key) {
        
    }

    @Override
    protected void onRemoveChannelKey(String channel, String sourceNick, String sourceLogin, String sourceHostname, String key) {
        
    }

    @Override
    protected void onSetChannelLimit(String channel, String sourceNick, String sourceLogin, String sourceHostname, int limit) {
        
    }

    @Override
    protected void onRemoveChannelLimit(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        
    }

    @Override
    protected void onSetChannelBan(String channel, String sourceNick, String sourceLogin, String sourceHostname, String hostmask) {
        
    }

    @Override
    protected void onRemoveChannelBan(String channel, String sourceNick, String sourceLogin, String sourceHostname, String hostmask) {
        
    }

    @Override
    protected void onSetTopicProtection(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        
    }

    @Override
    protected void onRemoveTopicProtection(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        
    }

    @Override
    protected void onSetNoExternalMessages(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        
    }

    @Override
    protected void onRemoveNoExternalMessages(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        
    }

    @Override
    protected void onSetInviteOnly(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        
    }

    @Override
    protected void onRemoveInviteOnly(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        
    }

    @Override
    protected void onSetModerated(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        
    }

    @Override
    protected void onRemoveModerated(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        
    }

    @Override
    protected void onSetPrivate(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        
    }

    @Override
    protected void onRemovePrivate(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        
    }

    @Override
    protected void onSetSecret(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        
    }

    @Override
    protected void onRemoveSecret(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        
    }

    @Override
    @SuppressWarnings("deprecation")
    protected void onDccSendRequest(String sourceNick, String sourceLogin, String sourceHostname, String filename, long address, int port, int size) {
        
    }

    @Override
    @SuppressWarnings("deprecation")
    protected void onDccChatRequest(String sourceNick, String sourceLogin, String sourceHostname, long address, int port) {
        
    }

    @Override
    protected void onIncomingFileTransfer(DccFileTransfer transfer) {
        
    }

    @Override
    protected void onFileTransferFinished(DccFileTransfer transfer, Exception e) {
        
    }

    @Override
    protected void onIncomingChatRequest(DccChat chat) {
        
    }
    
    /* Help */
    
    private static Map<String, String> help;
    
    static {
        help = new HashMap<String, String>();
        help.put(Strings.COMMAND_HELP, Strings.HELP_HELP);
        help.put(Strings.COMMAND_LOGIN, Strings.HELP_LOGIN);
        help.put(Strings.COMMAND_REGISTER, Strings.HELP_REGISTER);
        help.put(Strings.COMMAND_INFOS, Strings.HELP_INFOS);
        help.put(Strings.COMMAND_ATTACK, Strings.HELP_ATTACK);
        help.put(Strings.COMMAND_MAGIC, Strings.HELP_MAGIC);
        help.put(Strings.COMMAND_STEAL, Strings.HELP_STEAL);
        help.put(Strings.COMMAND_WORK, Strings.HELP_WORK);
        help.put(Strings.COMMAND_REST, Strings.HELP_REST);
        help.put(Strings.COMMAND_TRAIN, Strings.HELP_TRAIN);
        help.put(Strings.COMMAND_RETURN, Strings.HELP_RETURN);
        help.put(Strings.COMMAND_BUY, Strings.HELP_BUY);
        help.put(Strings.COMMAND_SELL, Strings.HELP_SELL);
        help.put(Strings.COMMAND_DRINK, Strings.HELP_DRINK);
        help.put(Strings.COMMAND_LEARN, Strings.HELP_LEARN);
        help.put(Strings.COMMAND_LEVELUP, Strings.HELP_LEVELUP);
        help.put(Strings.COMMAND_LOOK, Strings.HELP_LOOK);
        help.put(Strings.COMMAND_STATS, Strings.HELP_STATS);
        help.put(Strings.COMMAND_ITEMS, Strings.HELP_ITEMS);
        help.put(Strings.COMMAND_SPELLS, Strings.HELP_SPELLS);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.debnet.ircrpg.robot;

import fr.debnet.ircbot.DccChat;
import fr.debnet.ircbot.DccFileTransfer;
import fr.debnet.ircbot.IRCBot;
import fr.debnet.ircbot.User;
import fr.debnet.ircrpg.Config;
import fr.debnet.ircrpg.Strings;
import fr.debnet.ircrpg.enums.Activity;
import fr.debnet.ircrpg.enums.Potion;
import fr.debnet.ircrpg.enums.Stat;
import fr.debnet.ircrpg.game.Game;
import fr.debnet.ircrpg.game.queues.INotifiable;
import fr.debnet.ircrpg.models.Player;
import fr.debnet.ircrpg.models.Result;
import java.util.logging.Logger;

/**
 *
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
            String command = words[0];
            if (!command.startsWith("!")) {
                return;
            }
            // Register
            if (Strings.COMMAND_REGISTER.equalsIgnoreCase(command)) {
                if (words.length == 3) {
                    String username = words[1];
                    String password = words[2];
                    if (this.game.register(username, password, sender, hostname)) {
                        this.sendFormattedMessage(sender, Strings.REGISTER_SUCCEED);
                    } else {
                        this.sendFormattedMessage(sender, Strings.REGISTER_FAILED);
                    }
                }
            }
            // Login
            else if (Strings.COMMAND_LOGIN.equalsIgnoreCase(command)) {
                if (words.length == 3) {
                    String username = words[1];
                    String password = words[2];
                    if (this.game.login(username, password, sender, hostname)) {
                        Player player = this.game.getPlayerByUsername(username);
                        this.sendFormattedMessage(Strings.LOGIN_SUCCEED, player.getNickname(), player.getLevel());
                    } else {
                        this.sendFormattedMessage(sender, Strings.LOGIN_FAILED);
                    }
                }
            }
            // Infos
            else if (Strings.COMMAND_INFOS.equalsIgnoreCase(command)) {
                Player player = null;
                boolean all = false; 
                if (words.length == 1) {
                    all = true;
                    player = this.game.getPlayerByNickname(sender);
                } else if (words.length == 2) {
                    String target = words[2];
                    all = false;
                    player = this.game.getPlayerByNickname(target);
                }
                if (player != null) {
                    // TODO:
                }
            }
            // Attack
            else if (Strings.COMMAND_ATTACK.equalsIgnoreCase(command)) {
                if (words.length == 2) {
                    String target = words[1];
                    Result result = this.game.fight(sender, target, null);
                    this.displayResult(result, sender);
                }
            }
            // Magic
            else if (Strings.COMMAND_MAGIC.equalsIgnoreCase(command)) {
                if (words.length > 2) {
                    String target = words[1];
                    String magic = "";
                    for (int i = 1; i < words.length - 2; i++) {
                        magic += " " + words[i];
                        magic = magic.trim();
                    }
                    Result result = this.game.fight(sender, target, magic);
                    this.displayResult(result, sender);
                }
            }
            // Steal
            else if (Strings.COMMAND_STEAL.equalsIgnoreCase(command)) {
                if (words.length == 2) {
                    String target = words[1];
                    Result result = this.game.steal(sender, target);
                    this.displayResult(result, sender);
                }
            }
            // Work
            else if (Strings.COMMAND_WORK.equalsIgnoreCase(command)) {
                if (words.length == 1) {
                    Result result = this.game.startActivity(sender, Activity.WORKING);
                    this.displayResult(result, sender);
                }
            }
            // Rest
            else if (Strings.COMMAND_REST.equalsIgnoreCase(command)) {
                if (words.length == 1) {
                    Result result = this.game.startActivity(sender, Activity.RESTING);
                    this.displayResult(result, sender);
                }
            }
            // Train
            else if (Strings.COMMAND_TRAIN.equalsIgnoreCase(command)) {
                if (words.length == 1) {
                    Result result = this.game.startActivity(sender, Activity.TRAINING);
                    this.displayResult(result, sender);
                }
            }
            // Return
            else if (Strings.COMMAND_RETURN.equalsIgnoreCase(command)) {
                if (words.length == 1) {
                    Result result = this.game.endActivity(sender);
                    this.displayResult(result, sender);
                }
            }
            // Buy
            else if (Strings.COMMAND_BUY.equalsIgnoreCase(command)) {
                if (words.length > 1) {
                    String item = "";
                    for (int i = 1; i < words.length - 1; i++) {
                        item += " " + words[i];
                        item = item.trim();
                    }
                    Result result = this.game.buy(sender, item);
                    this.displayResult(result, sender);
                }
            }
            // Sell
            else if (Strings.COMMAND_SELL.equalsIgnoreCase(command)) {
                if (words.length > 1) {
                    String item = "";
                    for (int i = 1; i < words.length - 1; i++) {
                        item += " " + words[i];
                        item = item.trim();
                    }
                    Result result = this.game.buy(sender, item);
                    this.displayResult(result, sender);
                }
            }
            // Drink
            else if (Strings.COMMAND_DRINK.equalsIgnoreCase(command)) {
                if (words.length > 1) {
                    String item = "";
                    for (int i = 1; i < words.length - 1; i++) {
                        item += " " + words[i];
                        item = item.trim();
                    }
                    Potion potion = Potion.NONE;
                    for (Potion p : Potion.values()) {
                        if (p.getText().equalsIgnoreCase(item)) {
                            potion = p;
                            break;
                        }
                    }
                    Result result = this.game.drink(sender, potion);
                    this.displayResult(result, sender);
                }
            }
            // Learn
            else if (Strings.COMMAND_LEARN.equalsIgnoreCase(command)) {
                if (words.length > 1) {
                    String spell = "";
                    for (int i = 1; i < words.length - 1; i++) {
                        spell += " " + words[i];
                        spell = spell.trim();
                    }
                    Result result = this.game.learn(sender, spell);
                    this.displayResult(result, sender);
                }
            }
            // Level up
            else if (Strings.COMMAND_LEVELUP.equalsIgnoreCase(command)) {
                if (words.length == 2) {
                    Stat stat = Stat.NONE;
                    for (Stat s : Stat.values()) {
                        if (s.getText().equalsIgnoreCase(words[1])) {
                            stat = s;
                            break;
                        }
                    }
                    Result result = this.game.levelUp(sender, stat);
                    this.displayResult(result, sender);
                }
            }
            // Look
            else if (Strings.COMMAND_LOOK.equalsIgnoreCase(command)) {
                if (words.length > 1) {
                    // TODO:
                }
            }
            // Stats
            else if (Strings.COMMAND_STATS.equalsIgnoreCase(command)) {
                if (words.length == 1) {
                    
                } else if (words.length == 2) {
                    
                }
            }
        }
    }
    
    private void displayResult(Result result, String sender) {
        if (result.isSuccess()) {
            this.sendFormattedMessage(result.getMessage());
        } else {
            this.sendFormattedMessage(sender, result.getMessage());
        }
    }
    
    @Override
    public void notify(Result result) {
        for (String channel : this.getChannels()) {
            this.sendFormattedMessage(channel, result.getMessage());
        }
    }
    
    @Override
    protected void onConnect() {
        
    }

    @Override
    protected void onDisconnect() {
        
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
        if (this.game.tryRelogin(sender, hostname)) {
            Player player = this.game.getPlayerByNickname(sender);
            this.sendFormattedMessage(Strings.LOGIN_SUCCEED, player.getNickname(), player.getLevel());
        }
    }

    @Override
    protected void onPart(String channel, String sender, String login, String hostname) {
        if (this.game.logout(sender)) {
            Player player = this.game.getPlayerByNickname(sender);
            this.sendFormattedMessage(Strings.LOGOUT_SUCCEED, player.getNickname(), player.getLevel());
        }
    }

    @Override
    protected void onNickChange(String oldNick, String login, String hostname, String newNick) {
        
    }

    @Override
    protected void onKick(String channel, String kickerNick, String kickerLogin, String kickerHostname, String recipientNick, String reason) {
        
    }

    @Override
    protected void onQuit(String sourceNick, String sourceLogin, String sourceHostname, String reason) {
        if (this.game.logout(sourceNick)) {
            //TODO:
        }
    }

    @Override
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
    protected void onDccSendRequest(String sourceNick, String sourceLogin, String sourceHostname, String filename, long address, int port, int size) {
        
    }

    @Override
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
}

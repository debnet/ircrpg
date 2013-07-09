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
    
    public void sendFormattedMessage(String message, Object... args) {
        if (this.isConnected()) {
            for (String channel : this.getChannels()) {
                message = String.format(message, args);
                message = Strings.formatMessage(message);
                super.sendMessage(channel, message);
            }
        }
    }
    
    public void sendFormattedMessage(String target, String message, Object... args) {
        if (this.isConnected()) {
            message = String.format(message, args);
            message = Strings.formatMessage(message);
            super.sendMessage(target, message);
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
        String[] words = message.split(" ");
        if (words.length > 0) {
            String first = words[0];
            // Register
            if (Strings.COMMAND_REGISTER.equalsIgnoreCase(first)) {
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
            else if (Strings.COMMAND_LOGIN.equalsIgnoreCase(first)) {
                if (words.length == 3) {
                    String username = words[1];
                    String password = words[2];
                    if (this.game.login(username, password, sender, hostname)) {
                        this.sendFormattedMessage(sender, Strings.LOGIN_SUCCEED);
                    } else {
                        this.sendFormattedMessage(sender, Strings.LOGIN_FAILED);
                    }
                }
            }
            // Infos
            else if (Strings.COMMAND_INFOS.equalsIgnoreCase(first)) {
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
                    
                }
            }
        }
    }

    @Override
    protected void onPrivateMessage(String sender, String login, String hostname, String message) {
        
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
            this.sendFormattedMessage(sender, Strings.RELOGIN_SUCCEED);
        }
    }

    @Override
    protected void onPart(String channel, String sender, String login, String hostname) {
        if (this.game.logout(sender)) {
            //TODO:
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

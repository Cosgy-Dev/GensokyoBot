/*
 * MIT License
 *
 * Copyright (c) 2017 Frederik Ar. Mikkelsen
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.frederikam.gensokyobot.command.music.control;

import com.frederikam.gensokyobot.audio.GuildPlayer;
import com.frederikam.gensokyobot.audio.PlayerRegistry;
import com.frederikam.gensokyobot.commandmeta.abs.Command;
import com.frederikam.gensokyobot.feature.I18n;
import com.frederikam.gensokyobot.commandmeta.abs.IMusicCommand;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LeaveCommand extends Command implements IMusicCommand {

    private static final Logger log = LoggerFactory.getLogger(LeaveCommand.class);

    @Override
    public void onInvoke(Guild guild, TextChannel channel, Member invoker, Message message, String[] args) {
        try {
            GuildPlayer player = PlayerRegistry.get(guild);
            player.setCurrentTC(channel);
            player.leaveVoiceChannelRequest(channel, false);
        } catch (Exception e) {
            log.error("\u4f55\u304b\u304c\u79c1\u305f\u3061\u306b\u97f3\u58f0\u30c1\u30e3\u30cd\u30eb\u3092\u6b63\u3057\u304f\u6b8b\u3055\u306a\u3044\u3088\u3046\u306b\u3057\u307e\u3057\u305f\uff01", e);
            guild.getAudioManager().closeAudioConnection();
        }
    }

    @Override
    public String help(Guild guild) {
        String usage = "{0}{1}\n#";
        return usage + I18n.get(guild).getString("helpLeaveCommand");
    }
}

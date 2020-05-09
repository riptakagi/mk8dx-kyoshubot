package me.notsmatch.kyoshubot.service

import com.mongodb.client.model.Filters
import me.notsmatch.kyoshubot.Bot
import me.notsmatch.kyoshubot.model.Boshu

class BoshuService() {

    /**
     * @param guildId
     * @param channelId
     * @return 募集を返します
     */
    fun getBoshu(guildId: Long, channelId: Long) : Boshu? {
        val document = Bot.mongoService.findBoshuById(guildId, channelId) ?: return null
        return Boshu.toBoshu(document)
    }

    /**
     * 募集を追加します
     * @param guildId
     * @param channelId
     * @return 該当のギルドのチャンネルで既に募集中の場合はfalseを返します
     */
    fun addBoshu(guildId: Long, channelId: Long, title: String) : Boolean {
        if (getBoshu(guildId, channelId) != null) return false
        Bot.mongoService.replaceBoshu(guildId, channelId, Boshu(guildId, channelId, title, 0, mutableListOf()).toDocument())
        return true
    }

    /**
     * 募集を削除します
     * @param guildId
     * @param channelId
     * @return 削除結果を返します
     */
    fun removeBoshu(guildId: Long, channelId: Long) : Boolean{
        val boshu = getBoshu(guildId, channelId)
        if(boshu === null)return false
        return Bot.mongoService.boshu_collection.deleteOne(Filters.and(Filters.eq("guildId", guildId), Filters.eq("channelId", channelId))).wasAcknowledged()
    }
}
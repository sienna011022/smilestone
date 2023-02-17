package com.smilestone.netty_server

import io.netty.channel.nio.NioEventLoopGroup
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class ApplicationStartupTask(
    val nettyServerSocket: NioEventLoopGroup
) : ApplicationListener<ApplicationReadyEvent> {
    override fun onApplicationEvent(event: ApplicationReadyEvent) {

    }
}
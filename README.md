# pg-client-152-repr
Reproducer for 
https://github.com/reactiverse/reactive-pg-client/issues/152

How to run:
`./gradlew test --info --stacktrace`

Expected result: test passes

Actual result: fails with
```
SEVERE: Unhandled exception
java.lang.NullPointerException
	at io.reactiverse.pgclient.impl.PgResultBuilder.handle(PgResultBuilder.java:65)
	at io.reactiverse.pgclient.impl.PgResultBuilder.handle(PgResultBuilder.java:30)
	at io.reactiverse.pgclient.impl.PgConnectionImpl.lambda$schedule$0(PgConnectionImpl.java:58)
	at io.reactiverse.pgclient.impl.SocketConnection.handleMessage(SocketConnection.java:227)
	at io.reactiverse.pgclient.impl.SocketConnection.lambda$initiateProtocol$1(SocketConnection.java:110)
	at io.vertx.core.net.impl.NetSocketImpl.handleMessageReceived(NetSocketImpl.java:351)
	at io.vertx.core.net.impl.NetClientImpl$1.handleMessage(NetClientImpl.java:242)
	at io.vertx.core.net.impl.NetClientImpl$1.handleMessage(NetClientImpl.java:239)
	at io.vertx.core.net.impl.VertxHandler.lambda$channelRead$1(VertxHandler.java:146)
	at io.vertx.core.impl.ContextImpl.lambda$wrapTask$2(ContextImpl.java:337)
	at io.vertx.core.impl.ContextImpl.executeFromIO(ContextImpl.java:195)
	at io.vertx.core.net.impl.VertxHandler.channelRead(VertxHandler.java:144)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:362)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:348)
	at io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:340)
	at io.reactiverse.pgclient.impl.CommandBase.handleReadyForQuery(CommandBase.java:126)
	at io.reactiverse.pgclient.impl.codec.decoder.MessageDecoder.decodeReadyForQuery(MessageDecoder.java:247)
	at io.reactiverse.pgclient.impl.codec.decoder.MessageDecoder.channelRead(MessageDecoder.java:106)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:362)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:348)
	at io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:340)
	at io.netty.channel.DefaultChannelPipeline$HeadContext.channelRead(DefaultChannelPipeline.java:1359)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:362)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:348)
	at io.netty.channel.DefaultChannelPipeline.fireChannelRead(DefaultChannelPipeline.java:935)
	at io.netty.channel.nio.AbstractNioByteChannel$NioByteUnsafe.read(AbstractNioByteChannel.java:141)
	at io.netty.channel.nio.NioEventLoop.processSelectedKey(NioEventLoop.java:645)
	at io.netty.channel.nio.NioEventLoop.processSelectedKeysOptimized(NioEventLoop.java:580)
	at io.netty.channel.nio.NioEventLoop.processSelectedKeys(NioEventLoop.java:497)
	at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:459)
	at io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:886)
	at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.lang.Thread.run(Thread.java:745)
```



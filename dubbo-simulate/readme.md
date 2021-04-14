

# 运行
启动provider: 直接运行com/zhiyuan/provider/Provider.java的main方法；
启动consumer: 直接运行com/zhiyuan/comsumer/Consumer.java的main方法；

默认采用的运行协议是http协议，

想要修改协议，需要在provider和consumer运行时添加Vm param -DprotocolName=dubbo.

#原理解析：

RPC远程调用需要模块如下：

1. 远程注册中心RemoteMapRegister：
    1. 远程组册中心内存储格式 map{接口，url}， 即：对于每个接口，存储提供该接口实现类所在的url,包含ip:port  
    2. provider在启动的时候，向远程注册中心注册自己实现的接口和url；同时，在本地注册中心注册{接口, object实现类}，因为一个provider可能同时提供很多的接口服务；
    3. consumer启动的时候，就从远程服务中心获取到实现了某个interface的所有url， 然后通过负载均衡，从中选择一个url，发送请求。 
    4. 从以上功能来看，一个远程中心只需要实现 regist(String interfaceName, URL url)和List<URL> get(String interfaceName)方法即可。参见RemoteMapRegister类；
    5. 代码还实现了zookeeper注册中心，但是需要本地开启zookeeper.
     
2. Provider 服务提供者：
    1. 服务提供者在启动的时候，需要做三件时：
        1. 注册服务到注册中心map{接口，url}，这里，我们模拟，将注册信息保存在硬盘中；
        2. 本地注册，map{接口，实现类}， 方便在收到请求后路由到对应的实现类进行处理；
        3. 根据不同的协议启动不同的服务，如：如果是Http协议，可以启动tomcat服务， 如果是Dubbo等自定义协议，可以启动Netty服务。
        
        
3. consumer 服务接受者：
    1. 为了使得服务调用者像调用本地方法一样，调用远程方法，需要通过动态代理，ProxyFactory.getProxy(HelloService.class);来实现远程方法调用，
    2. 具体调用时，要根据不同的协议选择不同的客户端连接，(httpClient或者netty).
    
4. 统一请求和响应参数
    1. Invocation类，远程调用需要使用确定调用的接口，方法，参数类型和参数， 因此，Invocation类需要包含以下方法；
        1. private String interfaceName;
        2. private String methodName;
        3. private Object[] params;
        4. private Class[] paramType;    
    
5. 负载均衡，
    1. 这里简单的写一个random随机负载均衡；        

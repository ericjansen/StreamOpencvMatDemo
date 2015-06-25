#stream opencv Mat to pc or android phone though tcp
it runs like follows:
![](demo.gif)

#sent to pc server
edit `streamMatClient\main.cpp` server port

```
tcp::endpoint end_point(boost::asio::ip::address::from_string("127.0.0.1"), 3200);
```

#sent to android server 
edit `streamMatClient\main.cpp` server port

```
tcp::endpoint end_point(boost::asio::ip::address::from_string("10.104.3.196"), 3200);  // 10.104.3.196 is my android phone ip ,plea edit it to your phones
```

#android server

androidServer.apk is pre-compiled on my android phone(4.2.2 api)

just clicked the start server socket button to run the android server and  it will get the image from pc client(streamMatClient)


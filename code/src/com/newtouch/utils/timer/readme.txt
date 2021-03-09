文件夹说明：
定时器以及定时器接口类文件夹。
需要系统定时抽取的内容可以定义到t_timerplan表中，并且继承TimerRunable接口，定时器类会自动调用并抽取。

类说明：
CMSTimer.java：定时任务启动类；配置在web.xml中，用于启动系统定时任务。
CMSTimerTask.java：初始化定时调度类，被定时任务启动类直接调用；在第一次被调用时会初始化CMSTimerDispatch类，对操作员和分页信息进行赋值。
CMSTimerDispatch.java：定时任务列表类，系统会每小时调用此定时器类；此类会读取t_timerplan表中定义的定时抽取业务类信息，定时调取具体的业务抽取类。

TimerRunable.java：定时抽取业务类接口，需要定时抽取的业务类均需要实现此接口。
AbsTimerRunable.java：定时抽取业务抽像方法，实现了TimerRunable接口中的部分功能，定时抽取业务类可以直接继承此抽像类。


## Servlet 生命周期

Servlet的生命周期可以简单地用三个方法来表示：

1. init()：仅执行一次，负责在装载Servlet时初始化Servlet对象

2. service() ：核心方法，一般HttpServlet中会有get,post两种处理方式。在调用doGet和doPost方法时会构造servletRequest和servletResponse请求和响应对象作为参数。

3. destory()：在停止并且卸载Servlet时执行，负责释放资源
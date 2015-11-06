
char str2[] = "Hello";
string str1 = "Hello World";

typedef int myint;// myint 等同于 int

const int MYNAME = 1;// const：常量，类似java的final


// 类
class Box
{
	private:
      double width;
	public:
      double length;
      double breadth;
      double height;
      double getWidth(void);// 成员函数声明
      Box();  // 构造函数
};
// 成员函数定义
double Box::getWidth(void)
{
    return width;
}
// 构造函数
Box::Box(void)
{
    cout << "Object is being created" << endl;
}
Box Box;

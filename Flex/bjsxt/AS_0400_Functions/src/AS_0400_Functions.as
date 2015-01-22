package {
	import flash.display.Sprite;

	public class AS_0400_Functions extends Sprite
	{
		public function AS_0400_Functions()
		{
			var traceParameter:Function = function (aParam:String) :void//void f(String aParam)
			{
    			trace(aParam);
			};
			traceParameter("hello"); // hello
			
			
			var traceArray:Array = new Array();
			traceArray[0] = function (aParam:String):void
			{
    			trace(aParam);
			};
			traceArray[0]("hello");
			
			f();
			f('lisi');
			
			f2(1, 3, 5, 4, 6, 8);
			
		}
		
		public function f(name:String = 'zhangsan') : void {
			trace(name);
		}
		
		public function f2(...args): void {
			
			trace(args.length);
		}
		
		
	}
}

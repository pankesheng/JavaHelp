package {
	import flash.display.Sprite;

	public class AS_0200_Variables extends Sprite
	{
		public function AS_0200_Variables()
		{
			var v1:int = 9; //int v1 = 9;
			var v2:uint = 10;
			var v3:Boolean = true;
			var v4:Number = 4.5;
			var v5:String = "HelloWorld";
			var v6:String = 'HelloWorld Again';
			var v7:* = 3;
			trace(v7);
			v7 = 'hello';
			trace(v7);
			var v8;
			trace("v8=",v8);
			var v9:String;
			trace(v9);
			var v10:Array = [2, 3, 4, 5];
			trace(v10);
			var v11:Object = {id:3, name:"zhangsan", age:18}; //map
			trace(v11.name);
			
		}
	}
}

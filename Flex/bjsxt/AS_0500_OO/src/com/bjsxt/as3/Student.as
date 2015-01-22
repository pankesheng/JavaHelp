package com.bjsxt.as3
{
	public class Student
	{
		private var _name:String = 'zhangsan';
		public function Student() {}
		public function set name(name:String) : void { //setName
			this._name = name;
		}
			
		public function get name():String {
			return this._name;
		}

	}
}
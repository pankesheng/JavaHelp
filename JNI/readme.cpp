

JNIEXPORT void JNICALL Java_com_chnic_jni_SayHellotoCPP_sayHello   
  (JNIEnv* env, jobject obj, jstring name)  
{  
    const char* pname = env->GetStringUTFChars(name, NULL);  
    cout << "Hello " << pname << endl;

    // 释放内存
    env->ReleaseStringUTFChars(name, pname);
} 

//
// Created by liuwencai on 16/4/1.
//

#include "com_xiazhiri_practice_util_NDKUtil.h"

JNIEXPORT jstring JNICALL Java_com_xiazhiri_practice_util_NDKUtil_getString(JNIEnv *env, jobject obj) {
    return env->NewStringUTF( (char *)"Hello Jni");
}



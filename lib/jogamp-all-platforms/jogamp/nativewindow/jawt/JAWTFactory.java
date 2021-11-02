/* !---- DO NOT EDIT: This file autogenerated by com/jogamp/gluegen/JavaEmitter.java on Sat Oct 10 03:10:50 CEST 2015 ----! */

package jogamp.nativewindow.jawt;

import java.security.*;
import jogamp.nativewindow.jawt.*;
import com.jogamp.common.os.Platform;
import com.jogamp.common.util.VersionNumber;
import com.jogamp.gluegen.runtime.*;
import com.jogamp.common.os.*;
import com.jogamp.common.nio.*;
import java.nio.*;

public class JAWTFactory {

  /** Defined as part of enum type "jobjectRefType" - CType: int */
  public static final int JNIInvalidRefType = 0x0;
  /** Defined as part of enum type "jobjectRefType" - CType: int */
  public static final int JNILocalRefType = 0x1;
  /** Defined as part of enum type "jobjectRefType" - CType: int */
  public static final int JNIGlobalRefType = 0x2;
  /** Defined as part of enum type "jobjectRefType" - CType: int */
  public static final int JNIWeakGlobalRefType = 0x3;
  /** CType: int */
  public static final int JNI_ENOMEM = ( - 0x4 );
  /** CType: int */
  public static final int JNI_EEXIST = ( - 0x5 );
  /** CType: int */
  public static final int JNI_ABORT = 0x2;
  /** CType: int */
  public static final int JAWT_LOCK_CLIP_CHANGED = 0x2;
  /** CType: int */
  public static final int JAWT_VERSION_1_3 = 0x10003;
  /** CType: int */
  public static final int JNI_ERR = ( - 0x1 );
  /** CType: int */
  public static final int JAWT_LOCK_ERROR = 0x1;
  /** CType: int */
  public static final int JAWT_VERSION_1_4 = 0x10004;
  /** CType: int */
  public static final int JAWT_LOCK_SURFACE_CHANGED = 0x8;
  /** CType: int */
  public static final int JNI_EVERSION = ( - 0x3 );
  /** CType: int */
  public static final int JNI_VERSION_1_1 = 0x10001;
  /** CType: int */
  public static final int JNI_VERSION_1_2 = 0x10002;
  /** CType: int */
  public static final int JNI_OK = 0x0;
  /** CType: int */
  public static final int JNI_VERSION_1_6 = 0x10006;
  /** CType: int */
  public static final int JNI_VERSION_1_4 = 0x10004;
  /** CType: int */
  public static final int JAWT_LOCK_BOUNDS_CHANGED = 0x4;
  /** CType: int */
  public static final int JNI_FALSE = 0x0;
  /** CType: int */
  public static final int JNI_EDETACHED = ( - 0x2 );
  /** CType: int */
  public static final int JNI_EINVAL = ( - 0x6 );
  /** CType: int */
  public static final int JNI_TRUE = 0x1;
  /** CType: int */
  public static final int JNI_COMMIT = 0x1;

  /** Interface to C language function: <br> <code>jboolean JAWT_GetAWT(JNIEnv *  env, JAWT *  awt)</code><br>   */
  public static boolean JAWT_GetAWT(JAWT awt)  {

        return JAWT_GetAWT1(((awt == null) ? null : awt.getBuffer()));
  }

  /** Entry point to C language function: <code>jboolean JAWT_GetAWT(JNIEnv *  env, JAWT *  awt)</code><br>   */
  private static native boolean JAWT_GetAWT1(ByteBuffer awt);


} // end of class JAWTFactory

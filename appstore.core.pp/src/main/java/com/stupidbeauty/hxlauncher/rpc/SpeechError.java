package com.stupidbeauty.hxlauncher.rpc;

import java.util.HashMap;
import java.util.Map;

/**
 * 错误码事件信息对象。
 */
public class SpeechError extends Exception
{
    private String b;
    private int a;

    /**
     * 获取错误码对应的文字描述。
     * @return 错误码对应的文字描述。
     */
    public String getErrorDescription()
    {
        String result="Unknown";

        Map<Integer, String> errorCodeMap=new HashMap<>();

        errorCodeMap.put(ErrorCode.ERROR_AUDIO_RECORD, "Error audio record");
        errorCodeMap.put(ErrorCode.ERROR_CLEARTEXT_HTTP_NOT_PERMITTED, "Error clear text http not permitted");
        errorCodeMap.put(ErrorCode.ERROR_NET_CONNECTSOCK, "Error net connect socket");
        errorCodeMap.put(ErrorCode.ERROR_NETWORK_TIMEOUT, "Error network time out");
        errorCodeMap.put(ErrorCode.ERROR_NETWORK_UNREACHABLE, "Error network unreachable");
        errorCodeMap.put(ErrorCode.ERROR_OVERFLOW, "Error overflow");
        errorCodeMap.put(ErrorCode.ERROR_PERMISSION_DENIED, "Error permission denied");
        errorCodeMap.put(ErrorCode.ERROR_UNKNOWN, "Error unknown");
        errorCodeMap.put(ErrorCode.SUCCESS, "Success");

        if (errorCodeMap.containsKey(a)) //有这个的对应说明
        {
            result=errorCodeMap.get(a);
        } //if (errorCodeMap.containsKey(a)) //有这个的对应说明

        return result;
    }

    /**
     * 获取错误码。
     * @return 此事件对应的错误码。
     */
    public int getErrorCode() {
        return this.a;
    }


    public SpeechError(int var1) {
        this.a = 0;
        this.b = "";
        this.a = var1;
        byte var2 = 3;
        if (var1 < 20001) {
            if (this.a == 10118) {
                var2 = 11;
            } else if (10106 != this.a && 10107 != this.a && 10124 != this.a) {
                if (this.a == 10110) {
                    var2 = 32;
                } else if (this.a == 10111) {
                    var2 = 28;
                } else if (this.a >= 10200 && this.a < 10300) {
                    var2 = 3;
                } else if (this.a != 10117 && this.a != 10101) {
                    if (this.a == 10113) {
                        var2 = 17;
                    } else if (this.a == 10116) {
                        var2 = 64;
                    } else if (this.a == 10121) {
                        var2 = 66;
                    } else if (this.a >= 10400 && this.a <= 10407) {
                        var2 = 18;
                    } else if (this.a >= 11000 && this.a < 11099) {
                        if (this.a == 11005) {
                            var2 = 23;
                        } else if (this.a == 11006) {
                            var2 = 24;
                        } else {
                            var2 = 18;
                        }
                    } else if (this.a == 10129) {
                        var2 = 19;
                    } else if (this.a == 10109) {
                        var2 = 20;
                    } else if (this.a == 10702) {
                        var2 = 21;
                    } else if (this.a >= 10500 && this.a < 10600) {
                        var2 = 22;
                    } else if (this.a >= 11200 && this.a <= 11250) {
                        var2 = 25;
                    } else if (this.a >= 14000 && this.a <= 14006) {
                        var2 = 31;
                    } else if (this.a >= 16000 && this.a <= 16006) {
                        var2 = 31;
                    } else if (11401 == this.a) {
                        var2 = 35;
                    } else if (11402 == this.a) {
                        var2 = 36;
                    } else if (11403 == this.a) {
                        var2 = 37;
                    } else if (11404 == this.a) {
                        var2 = 38;
                    } else if (11405 == this.a) {
                        var2 = 39;
                    } else if (11406 == this.a) {
                        var2 = 40;
                    } else if (11407 == this.a) {
                        var2 = 41;
                    } else if (11408 == this.a) {
                        var2 = 42;
                    } else if (this.a == 11501) {
                        var2 = 65;
                    } else if (this.a == 11502) {
                        var2 = 64;
                    } else if (this.a == 11503) {
                        var2 = 19;
                    }
                } else {
                    var2 = 16;
                }
            } else {

                var2 = 7;
            }
        } else if (this.a < 30000) {
            if (this.a == 20001) {
                var2 = 1;
            } else if (this.a == 20002) {
                var2 = 2;
            } else if (this.a == 20003) {
                var2 = 3;
            } else if (this.a == 20004) {
                var2 = 5;
            } else if (this.a == 20005) {
                var2 = 10;
            } else if (this.a == 20006) {
                var2 = 9;
            } else if (this.a == 20007) {
                var2 = 12;
            } else if (this.a == 20008) {
                var2 = 11;
            } else if (this.a == 20009) {
                var2 = 13;
            } else if (this.a == 20010) {
                var2 = 14;
            } else if (this.a == 20012) {
                var2 = 7;
            } else if (this.a == 21003) {
                var2 = 28;
            } else if (this.a != 21002 && this.a != 21001) {
                var2 = 30;
            } else {
                var2 = 29;
            }
        }

        switch(this.a) {
            case 10031:
                var2 = 65;
                break;
            case 10141:
                var2 = 68;
                break;
            case 10142:
                var2 = 69;
                break;
            case 10143:
                var2 = 67;
                break;
            case 10144:
                var2 = 70;
                break;
            case 11600:
                var2 = 55;
                break;
            case 11601:
                var2 = 56;
                break;
            case 11602:
                var2 = 57;
                break;
            case 11603:
                var2 = 58;
                break;
            case 11604:
                var2 = 59;
                break;
            case 11605:
                var2 = 60;
                break;
            case 11606:
                var2 = 61;
                break;
            case 11607:
                var2 = 62;
                break;
            case 11608:
                var2 = 63;
                break;
            case 11610:
            case 11712:
                var2 = 64;
                break;
            case 11700:
                var2 = 43;
                break;
            case 11701:
                var2 = 44;
                break;
            case 11702:
                var2 = 45;
                break;
            case 11703:
                var2 = 46;
                break;
            case 11704:
                var2 = 47;
                break;
            case 11705:
                var2 = 48;
                break;
            case 11706:
                var2 = 49;
                break;
            case 11707:
                var2 = 50;
                break;
            case 11708:
                var2 = 51;
                break;
            case 11709:
                var2 = 52;
                break;
            case 11710:
                var2 = 53;
                break;
            case 11711:
                var2 = 54;
        }

        this.b = Resource.getErrorDescription(var2);
    }

}

package controller;

/**
 *
 * @author The Miracle Invoker
 */
public interface Acceptable {

    public final String STU_VALID_ID = "^[CcDdHhSsQq][Ee]\\%d{6}$";
    public final String NAME_VALID = "^.{2-20}$";
    public final String DOUBLE_VALID = "^-?\\\\d*\\\\.?\\\\d+$";
    public final String INTEGER_VALID = "\\d+";
    public final String PHONE_VALID = "^^(03|05|07|08|09)\\d{8}$";
    public final String VIETTEL_VALID = "^0(3[2-9]|86|9[678])[0-9]{7}";
    /*096, 097, 098, 032, 033, 034, 035, 036, 037, 038, 039, 086*/
    public final String VNPT_VALID = "^0(9[14]|8[1-58])[0-9]{7}";
    /*091, 094, 088, 081, 082, 083, 084, 085*/
    public final String EMAIL_VALID = "^[a-z0-9]+@fpt.+[a-z{2-10}]+.+[a-z{2-10}]";
    public final String YESNO_VALID = "^(?i)(y|n|yes|no)$";
    public final String MOUNTAIN_CODE_VALID = "^[0-9]{2}$";
    public final String CAMPUS_CODE_VALID = "^[CcDdHhSsQq][Ee]$";

    public static boolean isValid(String data, String pattern) {
        return data.matches(pattern);
    }

}

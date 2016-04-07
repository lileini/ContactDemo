package com.example.administrator.contactdemo.contact;

import android.text.SpannableString;
import android.text.TextUtils;

import com.github.promeg.pinyinhelper.Pinyin;
import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;

public class Phone implements Comparable<Phone>{
    public static final String HOME = "Home"; // =1
    public static final String WORK = "Work"; // =3
    public static final String MOBILE = "Mobile"; // =2
    public static final String OTHER = "Other"; // default

    @Id
    private long id;
    @Column(column = "type")
    private String type;
    @Column(column = "number")
    private String number;
    @Column(column = "photo_thumbnail")
    private String photo_thumbnail;
    @Column(column = "contactId")
    private String contactId;
    @Column(column = "contactName")
    private String contactName;
    @Column(column = "contactVersion")
    private String contactVersion;
    @Column(column = "countryCode")
    private String countryCode = "";
    /**
     * grucType stand for is friend , gruc or not all
     * 0 stand for not all
     * 1 stand for friend
     * 2 stand for gruc
     */
    @Column(column = "grucType")
    private int grucType = GrucType.SYSTEM;

    private SpannableString spannableName;
    private SpannableString spannableNumber;
    private int searchNameIndex;
    private int searchNumberIndex;

    private char letter;
    private int letterByNum; // make it as a tab to order (0 ~ 26 )

    //gruc 属性
    public String gruc_photo = null;
    public String gruc_name = "";

    public String getGruc_name() {
        return gruc_name;
    }

    public void setGruc_name(String gruc_name) {
        this.gruc_name = gruc_name;
    }

    public String getGruc_photo() {
        return gruc_photo;
    }

    public void setGruc_photo(String gruc_photo) {
        this.gruc_photo = gruc_photo;
    }
    public static class GrucType{

        public static final int SYSTEM = 0; // default
        public static final int FRIEND = 1;
        public static final int GRUC = 2;
    }
    /**
     *
     * @return 0 stand for not all
     * 1 stand for friend
     * 2 stand for gruc
     */
    public int getGrucType() {
        return grucType;
    }

    /**
     *
     * @param grucType 0 stand for not all
     * 1 stand for friend
     * 2 stand for gruc
     */
    public void setGrucType(int grucType) {
        this.grucType = grucType;
    }

    public Phone(String type, String number, String photo_thumbnail, String contactId, String contactName) {
        int i;
        try {
            i = Integer.parseInt(type);
        } catch (Exception e) {
            i = -1;
        }
        switch (i) {
            case 1:
                this.type = HOME;
                break;
            case 2:
                this.type = MOBILE;
                break;
            case 3:
                this.type = WORK;
                break;
            default:
                this.type = OTHER;
                break;
        }
        setNumber(number);
        setPhoto_thumbnail(photo_thumbnail);
        setContactId(contactId);
        setContactName(contactName);
    }

    public Phone(String contactName, String contactId) {
        this.contactName = contactName;
        this.contactId = contactId;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setLetter(){
        if (contactName == null || "".equals(contactName)) {
            letter = '#';
            letterByNum = 'Z' - 'A' + 1;
            return;
        }

        char temp = contactName.charAt(0);
        String pinYin;
        char c;
        if (Pinyin.isChinese(temp)){
            pinYin = Pinyin.toPinyin(temp);
            c =pinYin.charAt(0);
        }else {
            c = contactName.charAt(0);
        }
        boolean isLetter = isLetter(c);
        if (isLetter) {
            letter = Character.toUpperCase(c);
            letterByNum = letter - 'A';
        } else {
            letter = '#';
            letterByNum = 'Z' - 'A' + 1;
        }
    }

    /**
     * 判断是不是字母
     * @param codePoint
     * @return
     */
    public  boolean isLetter(int codePoint) {
        if (('A' <= codePoint && codePoint <= 'Z') || ('a' <= codePoint && codePoint <= 'z')) {
            return true;
        }
        return false;
    }
    public char getLetter(){
        return letter;
    }
    public int getLetterByNum() {
        return letterByNum;
    }


    public Phone() {

    }

    public Phone(Phone phone) {
        setNumber(phone.getNumber());
        setCountryCode(phone.getCountryCode());
        setPhoto_thumbnail(phone.getPhoto_thumbnail());
        setContactId(phone.getContactId());
        setContactName(phone.getContactName());
        setType(phone.getType());
    }

    public String getType() {
        return type;
    }

    /**
     * @param type {@use Phone.WORK}
     */
    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        String rs = CountryCodeDao.isContainsCountryCode(number);
        if (!TextUtils.isEmpty(rs)) {
            this.number = number.substring(rs.length());
            setCountryCode(rs);
        } else
            this.number = number.replace(" ", "").replace("-", "");
    }

    public String getContactVersion() {
        return contactVersion;
    }

    public void setContactVersion(String contactVersion) {
        this.contactVersion = contactVersion;
    }


    public String getPhoto_thumbnail() {
        return photo_thumbnail;
    }

    public void setPhoto_thumbnail(String photo_thumbnail) {
        this.photo_thumbnail = photo_thumbnail;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        if (contactName == null) {
            contactName = "";
        }
        return contactName;
    }


    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public SpannableString getSpannableName() {
        return spannableName;
    }

    public void setSpannableName(SpannableString spannableName) {
        this.spannableName = spannableName;
    }

    public SpannableString getSpannableNumber() {
        return spannableNumber;
    }

    public void setSpannableNumber(SpannableString spannableNumber) {
        this.spannableNumber = spannableNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }


    public int getSearchNameIndex() {
        return searchNameIndex;
    }

    public void setSearchNameIndex(int searchNameIndex) {
        this.searchNameIndex = searchNameIndex;
    }

    public int getSearchNumberIndex() {
        return searchNumberIndex;
    }

    public void setSearchNumberIndex(int searchNumberIndex) {
        this.searchNumberIndex = searchNumberIndex;
    }



    @Override
    public int compareTo(Phone another) {
        if (this.getLetterByNum() != another.getLetterByNum()) {
            return this.getLetterByNum() - another.getLetterByNum();
        }
        return stringPlus(this.getContactName(), another.getContactName());
    }

    /**
     * 如果前面两个字母相同依次比较后面的字母的unicode码
     * @param one
     * @param two
     * @return
     */
    public int stringPlus(String one, String two) {
        int lengthOne = one.length();
        int lengthTwo = two.length();
        if (lengthOne == 0) return -1;
        if (lengthTwo == 0) return 1;
        int first = one.charAt(0);
        int second = two.charAt(0);
        if (first == second) {
            return stringPlus(one.substring(1, lengthOne), two.substring(1, lengthTwo));
        } else {
            return first - second;
        }
    }
}

package com.example.administrator.contactdemo.contact;

public class Contact{
    /*public static final String mobile = "2";
    public static final String work = "3";
    public static final String home = "1";
    private static final String TAG = "Contact";
    private String contactId;
    private String contactName;
    // private List<String> homeNum, workNum, moblieNum;
//    @Column(name = "listPhones")
    private List<Phone> listPhones = new ArrayList<Phone>();
    private String photo_thumbnail;
    private String photo;
    private char letter;
    private int letterByNum; // make it as a tab to order (0 ~ 26 )

    private String contactVersion;
    private SpannableString spannableName;



    private SpannableString spannableNumber;
    private boolean hasQueryPhone = false;

    public char getLetter() {
        return letter;
    }

    public List<Phone> getPhones() {
        return listPhones;
    }

    public boolean addPhone(Phone phone) {
        for (Phone p : listPhones) {
            if (p.getNumber().equals(phone.getNumber())) return false;
        }
        listPhones.add(phone);
        return true;
    }

    public SpannableString getSpannableName() {
        return spannableName;
    }

    public SpannableString getSpannableNumber() {
        return spannableNumber;
    }

    public void setSpannableName(SpannableString spannableName) {
        this.spannableName = spannableName;
    }

    public void setSpannableNumber(SpannableString spannableNumber) {
        this.spannableNumber = spannableNumber;
    }

    public String getContactVersion() {
        return contactVersion;
    }
    public void setContactVersion(String contactVersion) {
        this.contactVersion = contactVersion;
    }
    public int getPhoneCount() {
        return listPhones.size() - 1;
    }

    public String getShowType() {
        if (listPhones.size() == 0)
            return null;
        return listPhones.get(0).type;
    }

    public String getNumber() {
        if (listPhones.size() == 0)
            return null;
        Phone phone = listPhones.get(0);
        return phone.getCountryCode()+phone.getNumber();
    }

    public String getContactName() {
        if (contactName == null) {
            contactName = " ";
        }
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;

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

       *//* char c;
        if (pinYin == null) {
            c = contactName.charAt(0);
        } else {
            c = pinYin.charAt(0);
        }*//*

        boolean isLetter = isLetter(c);
        if (isLetter) {
            letter = Character.toUpperCase(c);
            letterByNum = letter - 'A';
        } else {
            letter = '#';
            letterByNum = 'Z' - 'A' + 1;
        }
    }

    public  boolean isLetter(int codePoint) {
        if (('A' <= codePoint && codePoint <= 'Z') || ('a' <= codePoint && codePoint <= 'z')) {
            return true;
        }
        return false;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getPhoto_thumbnail() {
        return photo_thumbnail;
    }

    public void setPhoto_thumbnail(String photo_thumbnail) {
        this.photo_thumbnail = photo_thumbnail;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("_id  == " + contactId);
        sb.append(" contactName == " + contactName + "; ~~");
        for (Phone p : listPhones) {
            sb.append(p.getType() + "~~");
            sb.append(p.getNumber() + "~~");
        }
        return sb.toString();
    }

    public int getLetterByNum() {
        return letterByNum;
    }

    public synchronized boolean isHasQueryPhone() {
        if (hasQueryPhone) {
            return hasQueryPhone;
        } else {
            hasQueryPhone = true;
            return false;
        }

    }

    public void setHasQueryPhone(boolean hasQueryPhone) {
        this.hasQueryPhone = hasQueryPhone;
    }

    @Override
    public int compareTo(Contact another) {
        if (this.getLetterByNum() != another.getLetterByNum()) {
            return this.getLetterByNum() - another.getLetterByNum();
        }
        return stringPlus(this.getContactName(), another.getContactName());
    }

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
    }*/

    /**
     * now the phone add at AsyncLoadContact and ContactAdapter
     */

}

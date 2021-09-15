package model;

import android.os.Parcel;
import android.os.Parcelable;

public class user implements Parcelable{
    private String name,age,address;

    public user(String name, String age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    protected user(Parcel in) {
        name = in.readString();
        age = in.readString();
        address = in.readString();
    }

    public static final Creator<user> CREATOR = new Creator<user>() {
        @Override
        public user createFromParcel(Parcel in) {
            return new user(in);
        }

        @Override
        public user[] newArray(int size) {
            return new user[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(age);
        dest.writeString(address);
    }
}

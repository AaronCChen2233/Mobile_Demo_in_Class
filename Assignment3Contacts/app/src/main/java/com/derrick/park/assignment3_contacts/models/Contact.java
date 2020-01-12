package com.derrick.park.assignment3_contacts.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import kotlinx.android.parcel.Parcelize;


public class Contact implements Parcelable {

    public Contact(String fristName,String lastName, String cell) {
        this.name = new Name(fristName,lastName);
        this.cell = cell;
    }

    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("name")
    @Expose
    private Name name;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("cell")
    @Expose
    private String cell;

    public String getGender() {
        return gender;
    }

    public Name getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public String getCell() {
        return cell;
    }

    @Override
    public String toString() {
        return String.format("%n%s%n%s%n%s%n%s", name, location, email, cell);
    }

    /**
     * Name {first: , last: }
     */
    public class Name implements Parcelable {
        public Name(String first, String last) {
            this.first = first;
            this.last = last;
        }

        @SerializedName("first")
        @Expose
        private String first;
        @SerializedName("last")
        @Expose
        private String last;

        public String getFirst() {
            return first;
        }

        public String getLast() {
            return last;
        }

        @Override
        public String toString() {
            return first + " " + last;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.first);
            dest.writeString(this.last);
        }

        protected Name(Parcel in) {
            this.first = in.readString();
            this.last = in.readString();
        }

        public final Creator<Name> CREATOR = new Creator<Name>() {
            @Override
            public Name createFromParcel(Parcel source) {
                return new Name(source);
            }

            @Override
            public Name[] newArray(int size) {
                return new Name[size];
            }
        };
    }

    /**
     * Location {street: , city: , state: , postcode: }
     */
    class Location implements Parcelable {
        @SerializedName("street")
        @Expose
        private Street street;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("state")
        @Expose
        private String province;
        @SerializedName("postcode")
        @Expose
        private String postcode;

        public Street getStreet() {
            return street;
        }

        public String getCity() {
            return city;
        }

        public String getProvince() {
            return province;
        }

        public String getPostcode() {
            return postcode;
        }

        @Override
        public String toString() {
            return street + ", " + city + ", " + province + " Canada " + postcode;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(this.street, flags);
            dest.writeString(this.city);
            dest.writeString(this.province);
            dest.writeString(this.postcode);
        }

        public Location() {
        }

        protected Location(Parcel in) {
            this.street = in.readParcelable(Street.class.getClassLoader());
            this.city = in.readString();
            this.province = in.readString();
            this.postcode = in.readString();
        }

        public final Creator<Location> CREATOR = new Creator<Location>() {
            @Override
            public Location createFromParcel(Parcel source) {
                return new Location(source);
            }

            @Override
            public Location[] newArray(int size) {
                return new Location[size];
            }
        };
    }

    class Street implements Parcelable {
        @SerializedName("number")
        @Expose
        private int number;
        @SerializedName("name")
        @Expose
        private String name;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.number);
            dest.writeString(this.name);
        }

        public Street() {
        }

        protected Street(Parcel in) {
            this.number = in.readInt();
            this.name = in.readString();
        }

        public final Creator<Street> CREATOR = new Creator<Street>() {
            @Override
            public Street createFromParcel(Parcel source) {
                return new Street(source);
            }

            @Override
            public Street[] newArray(int size) {
                return new Street[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.gender);
        dest.writeParcelable(this.name, flags);
        dest.writeParcelable(this.location, flags);
        dest.writeString(this.email);
        dest.writeString(this.cell);
    }

    protected Contact(Parcel in) {
        this.gender = in.readString();
        this.name = in.readParcelable(Name.class.getClassLoader());
        this.location = in.readParcelable(Location.class.getClassLoader());
        this.email = in.readString();
        this.cell = in.readString();
    }

    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}


package ru.evotor.framework.receipt;

import android.os.Parcel;
import android.os.Parcelable;

public class Payment implements Parcelable {

    private final PaymentType paymentType;
    private final String userDescription;

    public Payment(PaymentType paymentType, String userDescription) {
        this.paymentType = paymentType;
        this.userDescription = userDescription;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment another = (Payment) o;

        if (paymentType != another.paymentType) {
            return false;
        }

        if (userDescription != null ? !userDescription.equals(another.userDescription) : another.userDescription != null)
            return false;

        return true;

    }

    @Override
    public int hashCode() {
        int result = userDescription != null ? userDescription.hashCode() : 0;
        result = 31 * result + (paymentType != null ? paymentType.hashCode() : 0);
        return result;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userDescription);
        dest.writeInt(this.paymentType.ordinal());
    }

    protected Payment(Parcel in) {
        this.userDescription = in.readString();
        this.paymentType = PaymentType.values()[in.readInt()];
    }

    public static final Creator<Payment> CREATOR = new Creator<Payment>() {
        @Override
        public Payment createFromParcel(Parcel source) {
            return new Payment(source);
        }

        @Override
        public Payment[] newArray(int size) {
            return new Payment[size];
        }
    };
}

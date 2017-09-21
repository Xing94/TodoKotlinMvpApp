package com.lucio.mvp.kotlinmvp.data.bean

import android.os.Parcel
import android.os.Parcelable

import com.lucio.mvp.kotlinmvp.base.BaseBean

/**
 * 登录实体类
 * 安装Parcelable插件一键生成
 */
class LoginBean : BaseBean, Parcelable {

    /**
     * userInfo : {"id":10356,"phone":"13122576190","nickName":"新用户","headImg":null,"gender":null,"birthday":null,"email":null,"addTime":1501108716430,"deviceId":null,"thirdId":null,"thirdType":null,"status":"0"}
     * reCode : 0
     * reMsg :
     */

    var userInfo: UserInfoBean? = null

    class UserInfoBean : Parcelable {
        /**
         * id : 10356
         * phone : 13122576190
         * nickName : 新用户
         * headImg : null
         * gender : null
         * birthday : null
         * email : null
         * addTime : 1501108716430
         * deviceId : null
         * thirdId : null
         * thirdType : null
         * status : 0
         */

        var id: Int = 0
        var phone: String? = null
        var nickName: String? = null
        var headImg: String? = null
        var gender: String? = null
        var birthday: String? = null
        var email: String? = null
        var addTime: Long = 0
        var deviceId: String? = null
        var thirdId: String? = null
        var thirdType: String? = null
        var status: String? = null


        override fun describeContents(): Int {
            return 0
        }

        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeInt(this.id)
            dest.writeString(this.phone)
            dest.writeString(this.nickName)
            dest.writeString(this.headImg)
            dest.writeString(this.gender)
            dest.writeString(this.birthday)
            dest.writeString(this.email)
            dest.writeLong(this.addTime)
            dest.writeString(this.deviceId)
            dest.writeString(this.thirdId)
            dest.writeString(this.thirdType)
            dest.writeString(this.status)
        }

        constructor() {}

        protected constructor(`in`: Parcel) {
            this.id = `in`.readInt()
            this.phone = `in`.readString()
            this.nickName = `in`.readString()
            this.headImg = `in`.readString()
            this.gender = `in`.readString()
            this.birthday = `in`.readString()
            this.email = `in`.readString()
            this.addTime = `in`.readLong()
            this.deviceId = `in`.readString()
            this.thirdId = `in`.readString()
            this.thirdType = `in`.readString()
            this.status = `in`.readString()
        }

        override fun toString(): String {
            return "UserInfoBean{" +
                    "id=" + id +
                    ", phone='" + phone + '\'' +
                    ", nickName='" + nickName + '\'' +
                    ", headImg='" + headImg + '\'' +
                    ", gender='" + gender + '\'' +
                    ", birthday='" + birthday + '\'' +
                    ", email='" + email + '\'' +
                    ", addTime=" + addTime +
                    ", deviceId='" + deviceId + '\'' +
                    ", thirdId='" + thirdId + '\'' +
                    ", thirdType='" + thirdType + '\'' +
                    ", status='" + status + '\'' +
                    '}'
        }

        companion object {

            val CREATOR: Parcelable.Creator<UserInfoBean> = object : Parcelable.Creator<UserInfoBean> {
                override fun createFromParcel(source: Parcel): UserInfoBean {
                    return UserInfoBean(source)
                }

                override fun newArray(size: Int): Array<UserInfoBean?> {
                    return arrayOfNulls(size)
                }
            }
        }
    }


    override fun toString(): String {
        return "LoginBean{" +
                "userInfo=" + userInfo +
                '}'
    }


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeParcelable(this.userInfo, flags)
    }

    constructor() {}

    protected constructor(`in`: Parcel) {
        this.userInfo = `in`.readParcelable<UserInfoBean>(UserInfoBean::class.java.classLoader)
    }

    companion object {

        val CREATOR: Parcelable.Creator<LoginBean> = object : Parcelable.Creator<LoginBean> {
            override fun createFromParcel(source: Parcel): LoginBean {
                return LoginBean(source)
            }

            override fun newArray(size: Int): Array<LoginBean?> {
                return arrayOfNulls(size)
            }
        }
    }
}

package com.kexu.tinfeed.profile;

import com.kexu.tinfeed.R;

import org.jetbrains.annotations.Contract;

enum Country {
    us(R.string.UnitedStates, R.string.us, R.drawable.ic_list_us),
    cn(R.string.China, R.string.cn, R.drawable.ic_list_cn),
    de(R.string.German, R.string.de, R.drawable.ic_list_de),
    in(R.string.India, R.string.in, R.drawable.ic_list_in),
    sg(R.string.Singapore, R.string.sg, R.drawable.ic_list_sg);

    private int country;
    private int countryUrl;
    private int countryFlag;

    Country(int country, int countryUrl, int countryFlag) {
        this.country = country;
        this.countryUrl = countryUrl;
        this.countryFlag = countryFlag;
    }

    @Contract(pure = true)
    int getCountryUrl() {
        return this.countryUrl;
    }

    @Contract(pure = true)
    int getCountry() {
        return this.country;
    }

    @Contract(pure = true)
    int getCountryFlag() {
        return this.countryFlag;
    }
}
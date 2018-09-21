package com.kex.tinfeed.profile;

import com.kex.tinfeed.R;

import org.jetbrains.annotations.Contract;

enum Country {
    us(R.string.UnitedStates, R.string.us),
    cn(R.string.China, R.string.cn),
    de(R.string.German, R.string.de),
    in(R.string.India, R.string.in);

    private int country;
    private int countryUrl;

    Country(int country, int countryUrl) {
        this.country = country;
        this.countryUrl = countryUrl;
    }

    @Contract(pure = true)
    int getCountryUrl() {
        return countryUrl;
    }

    @Contract(pure = true)
    int getCountry() {
        return country;
    }
}
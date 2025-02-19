package com.dentsu.bootcamp.controller;

import com.dentsu.bootcamp.dto.ProfileDTO;
import com.dentsu.bootcamp.service.ProfileService;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("enroll")
public class ProfileController {
    private ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/profileSearch")
    public Maybe<ProfileDTO> searchUserProfile(
            @RequestParam String driversLicenseNumber,
            @RequestParam String lastName,
            @RequestParam String issuingCountry,
            @RequestParam(required = false) String issuingAuthority) {

        return profileService.userProfileSearch(driversLicenseNumber, lastName, issuingCountry, issuingAuthority);
    }

    @PostMapping("/createProfile")
    public Single<ProfileDTO> submitPersonalInformation(@RequestBody @Valid ProfileDTO profile) {
        return profileService.submitPersonalInformation(profile);
    }

    @GetMapping("/{loyaltyNumber}")
    public ProfileDTO getProfile(@PathVariable String loyaltyNumber) {
        return profileService.getProfile(loyaltyNumber);
    }

    @PutMapping("/editProfile")
    public Single<ProfileDTO> editProfile(@RequestParam String loyaltyNumber, @RequestBody ProfileDTO updatedProfile) {
        return profileService.editPersonalInformation(loyaltyNumber, updatedProfile);
    }

    @GetMapping("/StatesAndProvinces/{country}")
    public List<String> getStatesAndProvinces(@PathVariable String country) {
        return profileService.getStatesAndProvinces(country);
    }
}

package com.example.relationshipJPA.Service;

import com.example.relationshipJPA.Entity.RefreshToken;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(String userName);

    RefreshToken verifyRefreshToken(String refreshToken);
}

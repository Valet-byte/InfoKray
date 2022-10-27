package com.valet.infokray.service.encoding;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EncodingServiceImpl implements EncodingService {

    private final PasswordEncoder encoder;

    @Override
    public String encode(String data) {
        return encoder.encode(data);
    }
}

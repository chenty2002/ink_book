package com.summerProject.ink_book.Service;

import com.summerProject.ink_book.Utils.Result;

public interface MailService {
    Result<String> sendEmail(String userEmail);
}

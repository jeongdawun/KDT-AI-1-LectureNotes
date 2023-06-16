package kr.eddi.demo.lectureClass.account.service;

import kr.eddi.demo.lectureClass.account.service.request.AccountRegisterRequest;
import kr.eddi.demo.lectureClass.account.controller.form.AccountResponseForm;

public interface AccountService {
    Boolean checkEmailDuplication(String email);

    Boolean signUp(AccountRegisterRequest request);

    Long findAccountIdByEmail(String email);

    Long signUpWithEmail(String email);

    AccountResponseForm findAccountInfoById(Long accountId);
}
package pfs.lms.enquiry.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pfs.lms.enquiry.repository.BankMasterRepository;
import pfs.lms.enquiry.service.IBankMasterService;

@Slf4j
@Service
@RequiredArgsConstructor
public class BankMasterService implements IBankMasterService {

    private final BankMasterRepository bankMasterRepository;


}

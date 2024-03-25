package com.example.springboot_project.service;

import com.example.springboot_project.dao.CurrencyRepository;
import com.example.springboot_project.dto.CurrencyDTO;
import com.example.springboot_project.entity.Currency;
import com.example.springboot_project.mapper.CurrencyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    private CurrencyRepository currencyRepository;
    private CurrencyMapper currencyMapper;


    @Autowired
    public CurrencyServiceImpl(CurrencyRepository currencyRepository, CurrencyMapper currencyMapper) {
        this.currencyRepository = currencyRepository;
        this.currencyMapper = currencyMapper;
    }

    @Override
    public List<CurrencyDTO> getAllCurrencyDTO() {
        List<Currency> allCurrencies = currencyRepository.findAll();
        List<CurrencyDTO> currenciesDTOs = allCurrencies.stream().map(currencyMapper::toDTO).toList();
        return currenciesDTOs;
    }

    @Override
    public void saveCurrency(CurrencyDTO currencyDTO) {
        currencyRepository.save(currencyMapper.dtoToCurrency(currencyDTO));
    }

    @Override
    public CurrencyDTO getCurrencyDTO(int id) {
        Currency currency = null;
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (optionalCurrency.isPresent()) {
            currency = optionalCurrency.get();
        }
        return currencyMapper.toDTO(currency);
    }

    @Override
    public void deleteCurrency(int id) {
        currencyRepository.deleteById(id);
    }
}

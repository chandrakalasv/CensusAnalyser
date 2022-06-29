package censusanalyser;


import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class CensusAnalyser {

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        String[] splitPath = csvFilePath.split("[.]");
        String extensionType = splitPath[splitPath.length - 1];
        if (!extensionType.equals("csv")) {
            throw new CensusAnalyserException("Invalid Extension", CensusAnalyserException.ExceptionType.CENSUS_TYPE_PROBLEM);
        }
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            CsvToBeanBuilder<IndiaCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IndiaCensusCSV.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IndiaCensusCSV> csvToBean = csvToBeanBuilder.build();
//            Iterator<IndiaCensusCSV> censusCSVIterator = csvToBean.iterator();
//            int namOfEateries = 0;
//            while (censusCSVIterator.hasNext()) {
//                namOfEateries++;
//                IndiaCensusCSV censusData = censusCSVIterator.next();
//            }
//            return namOfEateries;
            List<IndiaCensusCSV> censusCSVList = csvToBean.parse();
            return censusCSVList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.WRONG_HEADER);
        }
    }

    public int loadIndiaStateCodeData(String csvFilePath) throws CensusAnalyserException {
        String[] splitPath = csvFilePath.split("[.]");
        String extensionType = splitPath[splitPath.length - 1];
        if (!extensionType.equals("csv")) {
            throw new CensusAnalyserException("Invalid Extension", CensusAnalyserException.ExceptionType.CENSUS_TYPE_PROBLEM);
        }
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            CsvToBeanBuilder<IndiaStateCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IndiaStateCSV.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IndiaStateCSV> csvToBean = csvToBeanBuilder.build();
//            Iterator<IndiaStateCSV> stateCSVIterator = csvToBean.iterator();
//            int namOfEateries = 0;
//            while (stateCSVIterator.hasNext()) {
//                namOfEateries++;
//                IndiaStateCSV censusData = stateCSVIterator.next();
//            }
//            return namOfEateries;
            List<IndiaStateCSV> censusCSVList = csvToBean.parse();
            return censusCSVList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.WRONG_HEADER);
        }
    }
}



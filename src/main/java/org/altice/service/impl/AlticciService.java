package org.altice.service.impl;

import io.quarkus.cache.CacheResult;
import org.altice.exception.AlticciInvalidIndexException;
import org.altice.model.Alticci;
import org.altice.service.AlticciServiceInterface;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AlticciService implements AlticciServiceInterface {

    private final int MAXSEQUENCEINDEX = 157;

    @CacheResult(cacheName = "only-index-alticci-cache")
    @Override
    public Alticci getOnlyIndexAlticciSequenceValues(int sequenceIndex) throws AlticciInvalidIndexException {

        if (sequenceIndex > MAXSEQUENCEINDEX) {
            throw new AlticciInvalidIndexException("Index must be lower than 158");
        }
        return new Alticci(sequenceIndex, this.alticciAlgorithm(sequenceIndex)[sequenceIndex]);
    }
    @CacheResult(cacheName = "all-sequence-index-alticci-cache")
    @Override
    public List<Alticci> getAllIndexAlticciSequenceValues(int sequenceIndex) throws AlticciInvalidIndexException {

        if (sequenceIndex > MAXSEQUENCEINDEX) {
            throw new AlticciInvalidIndexException("Index must be lower than 158");
        }

        long[] sequenceValues = this.alticciAlgorithm(sequenceIndex);
        List<Alticci> allSequenceAlticciValues = new ArrayList<>();

        for (int i = 0; i < sequenceValues.length; i++) {
            allSequenceAlticciValues.add(new Alticci(i, sequenceValues[i]));
        }


        return allSequenceAlticciValues;
    }

    private long[] alticciAlgorithm(int sequenceIndex) {
        long firstTerm = 0;
        long arr[] = new long[sequenceIndex + 1];
        arr[0] = firstTerm;

        if (sequenceIndex == 0) {
            return arr;
        }

        long secondTerm = 1;
        for (int i = 1; i <= sequenceIndex; i++) {
            if (i < 3) {
                arr[i] = secondTerm;
            } else {
                firstTerm = arr[i - 3];
                secondTerm = arr[i - 2];
                arr[i] = firstTerm + secondTerm;
            }
        }
        return arr;
    }
}

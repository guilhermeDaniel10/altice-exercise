package org.altice.service;

import org.altice.exception.AlticciInvalidIndexException;
import org.altice.model.Alticci;

import java.util.List;

public interface AlticciServiceInterface {

    Alticci getOnlyIndexAlticciSequenceValues(int sequenceIndex) throws AlticciInvalidIndexException;

    List<Alticci> getAllIndexAlticciSequenceValues(int sequenceIndex) throws AlticciInvalidIndexException;
}

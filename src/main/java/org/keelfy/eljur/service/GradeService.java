package org.keelfy.eljur.service;

import org.keelfy.eljur.data.Grade;
import org.keelfy.eljur.model.request.RateStudentRequest;

/**
 * @author Egor Kuzmin
 */
public interface GradeService {

    Grade rateStudent(RateStudentRequest request);

}

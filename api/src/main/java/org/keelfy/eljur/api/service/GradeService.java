package org.keelfy.eljur.api.service;

import org.keelfy.eljur.data.entity.Grade;
import org.keelfy.eljur.api.model.request.RateStudentRequest;

/**
 * @author Egor Kuzmin
 */
public interface GradeService {

    Grade rateStudent(RateStudentRequest request);

}

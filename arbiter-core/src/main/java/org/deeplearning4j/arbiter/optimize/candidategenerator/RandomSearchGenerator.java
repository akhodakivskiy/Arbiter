/*
 *
 *  * Copyright 2016 Skymind,Inc.
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  *    limitations under the License.
 *
 */
package org.deeplearning4j.arbiter.optimize.candidategenerator;

import org.deeplearning4j.arbiter.optimize.api.Candidate;
import org.deeplearning4j.arbiter.optimize.api.ParameterSpace;

/**
 * RandomSearchGenerator: generates candidates at random.<br>
 * Note: if a probability distribution is provided for continuous hyperparameters, this will be taken into account
 * when generating candidates. This allows the search to be weighted more towards certain values according to a probability
 * density. For example: generate samples for learning rate according to log uniform distribution
 *
 * @param <T> Type of candidates to generate
 * @author Alex Black
 */
public class RandomSearchGenerator<T> extends BaseCandidateGenerator<T> {

    public RandomSearchGenerator(ParameterSpace<T> parameterSpace) {
        super(parameterSpace);

        initialize();
    }


    @Override
    public boolean hasMoreCandidates() {
        return true;
    }

    @Override
    public Candidate<T> getCandidate() {

        double[] randomValues = new double[parameterSpace.numParameters()];
        for (int i = 0; i < randomValues.length; i++) randomValues[i] = rng.nextDouble();

        return new Candidate<T>(parameterSpace.getValue(randomValues), candidateCounter.getAndIncrement(), randomValues);
    }

    @Override
    public String toString() {
        return "RandomSearchGenerator";
    }
}

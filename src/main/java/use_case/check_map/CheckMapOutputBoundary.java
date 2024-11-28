package use_case.check_map;

public interface CheckMapOutputBoundary {
        /**
         * Prepares the success view for Check Map Use Case.
         * @param outputData the output data
         */
        void prepareSuccessView(CheckMapOutputData outputData);

        /**
         * Prepares the failure view for the Check Map Use Case.
         * @param errorMessage the explanation of the failure
         */
        void prepareFailView(String errorMessage);

        /**
        * Switches to List of Reviews View.
        */
        void switchToListReviewView();
}

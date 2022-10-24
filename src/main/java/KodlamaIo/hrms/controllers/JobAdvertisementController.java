package KodlamaIo.hrms.controllers;

import KodlamaIo.hrms.business.abstracts.JobAdvertisementService;
import KodlamaIo.hrms.core.utilities.results.DataResult;
import KodlamaIo.hrms.core.utilities.results.Result;
import KodlamaIo.hrms.entity.concretes.JobAdvertisement;
import KodlamaIo.hrms.payload.request.JobAdvertisementRequest;
import KodlamaIo.hrms.payload.response.AdvertResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/jobAdvert")
@CrossOrigin
public class JobAdvertisementController {

    private JobAdvertisementService jobAdvertisementService;

    public JobAdvertisementController(JobAdvertisementService jobAdvertisementService) {
        this.jobAdvertisementService = jobAdvertisementService;
    }

    @GetMapping("/getAllAdvert")
    public DataResult<Page<AdvertResponse>> getAllAdvert(@RequestParam(name = "page",defaultValue = "1")
                                                         Integer page,
                                                         @RequestParam(name = "size",defaultValue = "10")
                                                         Integer size) {
        return this.jobAdvertisementService.getAllAdvert(page, size);
    }

    @PostMapping("/createAdvert")
    public Result createAdvert(@RequestBody @Valid JobAdvertisementRequest jobAdvertisementRequest) {
        System.out.println(jobAdvertisementRequest);
        return this.jobAdvertisementService.createAdvertisement(jobAdvertisementRequest);
    }

    @DeleteMapping("/deleteJobAdvert")
    public Result deleteJobAdvert(@RequestParam(name = "id") long id) {
        return this.jobAdvertisementService.deleteJobAdvert(id);
    }

    @GetMapping("/changeStatusOfJobAdvert")
    public DataResult<AdvertResponse> changeStatusOfJobAdvert(@RequestParam(name = "id") long id) {
        return this.jobAdvertisementService.changeStatusOfJobAdvert(id);
    }

    @GetMapping("/getJobAdvertsByActive")
    public DataResult<Page<AdvertResponse>> getJobAdvertsByActive(Pageable pageable) {
        return this.jobAdvertisementService.getJobAdvertsByActive(pageable);
    }

    @GetMapping("/getJobAdvertByCompanyNameAndJobConditionIsTrue")
    public DataResult<Page<AdvertResponse>> getJobAdvertByCompanyNameAndJobConditionIsTrue(@RequestParam(name = "email") String email,
                                                                                           @RequestParam(required = false) Pageable pageable) {
        return this.jobAdvertisementService.getJobAdvertByCompanyNameAndJobConditionIsTrue(email, pageable);
    }

    @GetMapping("/getByJobConditionTrueOrderByReleaseDate")
    public DataResult<Page<AdvertResponse>> getByJobConditionTrueOrderByReleaseDateDesc(Pageable pageable) {
        return this.jobAdvertisementService.getByJobConditionTrueOrderByReleaseDateDesc(pageable);
    }
}

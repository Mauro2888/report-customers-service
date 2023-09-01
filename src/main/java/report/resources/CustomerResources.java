package report.resources;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.ExampleObject;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.resteasy.reactive.RestPath;
import report.model.dto.CustomerDto;
import report.model.dto.CustomerRegistrationRequest;
import report.model.dto.CustomerUpdateDto;
import report.model.dto.ReportRequestDto;
import report.model.entity.Customer;
import report.service.CustomerServiceReport;
import report.service.CustomerService;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.UUID;

@Path("api/v1/customers")
public class CustomerResources {

    private final CustomerServiceReport customerServiceReport;

    private final CustomerService customerService;

    public CustomerResources(CustomerServiceReport customerServiceReport, CustomerService customerService) {
        this.customerServiceReport = customerServiceReport;
        this.customerService = customerService;
    }

    @Operation(summary = "Generate report from customer data")
    @APIResponse(responseCode = "201",
            content = @Content(mediaType = MediaType.APPLICATION_JSON),
            description = "Report generated")
    @POST
    @Path("/report")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportFile(@Valid ReportRequestDto reportRequest, @Context UriInfo uriInfo) {
        return Response.ok(customerServiceReport.createReport(reportRequest)).build();
    }

    @Operation(summary = "Get all customers data")
    @APIResponse(responseCode = "200",
            content = @Content(mediaType = MediaType.APPLICATION_JSON),
            description = "List of customers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CustomerDto> customers() {
        return customerService.customers();
    }

    @Operation(summary = "Save customer data")
    @APIResponse(responseCode = "201",
            content = @Content(mediaType = MediaType.APPLICATION_JSON),
            description = "Save customer")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response persistCustomer(
            @Valid CustomerRegistrationRequest customerRegistrationRequest, @Context UriInfo uriInfo) {
        customerService.saveCustomer(customerRegistrationRequest);
        return Response.created(uriInfo.getAbsolutePathBuilder().build()).build();
    }

    @Operation(summary = "Update customer address")
    @APIResponse(responseCode = "200",
            content = @Content(mediaType = MediaType.APPLICATION_JSON),
            description = "Update customer address")
    @PATCH
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomerAddress(@PathParam("id") UUID id,  @Valid CustomerUpdateDto.Request.UpdateCustomerAddress customerUpdate){
        CustomerDto updateCustomer = customerService.updateCustomer(id, customerUpdate);
        return Response.ok(updateCustomer).build();
    }

}

import {Button, Container, Grid, MenuItem, TextField} from "@mui/material";

interface ConvType {
    convTypeId: number;
    description: string;
}

const fieldWidth = {
    width: '8vw'
}

const gridMarginRight = {
    mr: 6
}

export const FilterTutors = () => {
    const convTypes: ConvType[] = [
        {convTypeId: 1, description: "Offline"},
        {convTypeId: 1, description: "Online"}
    ]


    return (
        <>
            <Container maxWidth="xl" sx={{position: 'fixed', backgroundColor: 'white', pb: 2}}>
                <Grid container sx={{mt: 8, ml: 10}} alignItems="flex-end">
                    <Grid item xs={1} sx={gridMarginRight}>
                        <TextField label="Min Price $" variant="standard" sx={fieldWidth}/>
                    </Grid>
                    <Grid item xs={1} sx={gridMarginRight}>
                        <TextField label="Max Price $" variant="standard" sx={fieldWidth}/>
                    </Grid>
                    <Grid item xs={1} sx={gridMarginRight}>
                        <TextField label="Country" variant="standard" sx={fieldWidth}/>
                    </Grid>
                    <Grid item xs={1} sx={gridMarginRight}>
                        <TextField
                            select
                            variant="standard"
                            label="City"
                            sx={fieldWidth}
                        >{convTypes.map((conv) => (
                            <MenuItem key={conv.convTypeId}>{conv.description}</MenuItem>
                        ))}
                        </TextField>
                    </Grid>
                    <Grid item xs={1} sx={gridMarginRight}>
                        <TextField
                            select
                            variant="standard"
                            label="Type"
                            sx={fieldWidth}
                        >{convTypes.map((conv) => (
                            <MenuItem key={conv.convTypeId}>{conv.description}</MenuItem>
                        ))}
                        </TextField>
                    </Grid>
                    <Grid item xs={1} sx={gridMarginRight}>
                        <TextField
                            select
                            variant="standard"
                            label="Language"
                            sx={fieldWidth}
                        >{convTypes.map((conv) => (
                            <MenuItem key={conv.convTypeId}>{conv.description}</MenuItem>
                        ))}
                        </TextField>
                    </Grid>
                    <Grid item xs={1} sx={gridMarginRight}>
                        <TextField
                            select
                            variant="standard"
                            label="Min Level"
                            sx={fieldWidth}
                        >{convTypes.map((conv) => (
                            <MenuItem key={conv.convTypeId}>{conv.description}</MenuItem>
                        ))}
                        </TextField>
                    </Grid>
                    <Grid item xs={1} sx={gridMarginRight}>
                        <Button variant="outlined">Apply</Button>
                    </Grid>
                </Grid>
            </Container>
        </>
    )
}